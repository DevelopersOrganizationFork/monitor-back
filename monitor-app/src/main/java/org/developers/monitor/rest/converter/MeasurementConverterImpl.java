package org.developers.monitor.rest.converter;

import org.developers.monitor.persistence.Cpu;
import org.developers.monitor.persistence.DAO.CpuDao;
import org.developers.monitor.persistence.DAO.MemoryDao;
import org.developers.monitor.persistence.DAO.NetworkDao;
import org.developers.monitor.persistence.Measurement;
import org.developers.monitor.persistence.Memory;
import org.developers.monitor.persistence.Network;
import org.developers.monitor.rest.dto.MeasurementDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by reynev on 5/4/15.
 */
@Component
public class MeasurementConverterImpl implements MeasurementConverter {

    private NetworkDao networkDao;

    private CpuDao cpuDao;

    private MemoryDao memoryDao;

    public MeasurementConverterImpl(){
        networkDao = new NetworkDao();
        cpuDao = new CpuDao();
        memoryDao = new MemoryDao();
    }

    @Override
    public List<MeasurementDTO> convertToDTO(Measurement measurement) {
        List<MeasurementDTO> convertedMeasurement = new ArrayList<>();

        MeasurementDTO cpuMeasurement = convertCPU(measurement);
        MeasurementDTO memoryMeasurement = convertMemory(measurement);
        MeasurementDTO networkUpMeasurement = convertNetworkUp(measurement);
        MeasurementDTO networkDownMeasurement = convertNetworkDown(measurement);

        convertedMeasurement.add(cpuMeasurement);
        convertedMeasurement.add(memoryMeasurement);
        convertedMeasurement.add(networkUpMeasurement);
        convertedMeasurement.add(networkDownMeasurement);

        return convertedMeasurement;
    }

    @Override
    public MeasurementDTO convertToDTO(Measurement measurement, MeasurementDTO.Type measurementType) {
        return null;
    }

    private MeasurementDTO convertNetworkUp(Measurement measurement) {
        MeasurementDTO measurementDTO = convertBasicData(measurement);
        measurementDTO.type = MeasurementDTO.Type.NETWORKUP;
        measurementDTO.value = computeNetworkUpValue(measurement);
        return measurementDTO;
    }

    private int computeNetworkUpValue(Measurement measurement) {
        int networkId = measurement.getMeasurementPK().getNetworknetworkId();
        Network network = networkDao.findById(networkId);
        int networkUpValue = network.getNetworkUpload().intValue();
        return networkUpValue;
    }

    private MeasurementDTO convertNetworkDown(Measurement measurement) {
        MeasurementDTO measurementDTO = convertBasicData(measurement);
        measurementDTO.type = MeasurementDTO.Type.NETWORKDOWN;
        measurementDTO.value = computeNetworkDownValue(measurement);
        return measurementDTO;
    }

    private int computeNetworkDownValue(Measurement measurement) {
        int networkId = measurement.getMeasurementPK().getNetworknetworkId();
        Network network = networkDao.findById(networkId);
        int networkDownValue = network.getNetworkDownload().intValue();
        return networkDownValue;
    }

    private MeasurementDTO convertMemory(Measurement measurement) {
        MeasurementDTO measurementDTO = convertBasicData(measurement);
        measurementDTO.type = MeasurementDTO.Type.MEMORY;
        measurementDTO.value = computeMemoryValue(measurement);
        return measurementDTO;
    }

    private double computeMemoryValue(Measurement measurement) {
        int memoryId = measurement.getMeasurementPK().getMemorymemoryId();
        Memory memory = memoryDao.findById(memoryId);
        double memoryUsed = memory.getMemoryUsed().doubleValue();
        double memoryTotal = memory.getMemoryTotal().doubleValue();
        double memoryValue = memoryUsed/memoryTotal;
        return memoryValue;
    }

    private MeasurementDTO convertCPU(Measurement measurement) {
        MeasurementDTO measurementDTO = convertBasicData(measurement);
        measurementDTO.type = MeasurementDTO.Type.CPU;
        measurementDTO.value = computeCPUValue(measurement);
        return measurementDTO;
    }

    private double computeCPUValue(Measurement measurement) {
        int cpuId = measurement.getMeasurementPK().getCPUCPUId();
        Cpu cpu = cpuDao.findById(cpuId);
        //co ja robię tuuu, co ja tutaj robieeeeee
        double cpuUsed = cpu.getCPUIrq().doubleValue() + cpu.getCPUSys().doubleValue() + cpu.getCPUUser().doubleValue();
        double cpuTotal = cpu.getCPUTotal().doubleValue();
        double cpuValue = cpuUsed/cpuTotal;
        return cpuValue;
    }

    private MeasurementDTO convertBasicData(Measurement measurement){
        MeasurementDTO measurementDTO = new MeasurementDTO();
        measurementDTO.date = measurement.getMeasurementDate();
        measurementDTO.id = measurement.getMeasurementPK().getMeasurementId();
        return measurementDTO;
    }


}
