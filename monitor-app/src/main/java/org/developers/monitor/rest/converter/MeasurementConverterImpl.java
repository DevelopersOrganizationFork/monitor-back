package org.developers.monitor.rest.converter;

import org.developers.monitor.persistence.Cpu;
import org.developers.monitor.persistence.DAO.CpuDao;
import org.developers.monitor.persistence.DAO.MemoryDao;
import org.developers.monitor.persistence.DAO.NetworkDao;
import org.developers.monitor.persistence.Measurement;
import org.developers.monitor.persistence.Memory;
import org.developers.monitor.persistence.Network;
import org.developers.monitor.rest.dto.MeasurementDTO;
import org.developers.monitor.rest.dto.MeasurementDTO.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.developers.monitor.rest.dto.MeasurementDTO.Type.CPU;

/**
 * Created by reynev on 5/4/15.
 */
@Service
public class MeasurementConverterImpl implements MeasurementConverter {

    @Autowired
    private NetworkDao networkDao;
    
    @Autowired
    private CpuDao cpuDao;
    
    @Autowired
    private MemoryDao memoryDao;

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
    public MeasurementDTO convertToDTO(Measurement measurement, Type measurementType) {
        MeasurementDTO measurementDTO = new MeasurementDTO();
        switch (measurementType){
            case CPU:
                measurementDTO = convertCPU(measurement);
                break;
            case MEMORY:
                measurementDTO = convertMemory(measurement);
                break;
            case NETWORKDOWN:
                measurementDTO = convertNetworkDown(measurement);
                break;
            case NETWORKUP:
                measurementDTO = convertNetworkUp(measurement);
                break;
        }
        return measurementDTO;
    }

    private MeasurementDTO convertNetworkUp(Measurement measurement) {
        MeasurementDTO measurementDTO = convertBasicData(measurement);
        measurementDTO.type = Type.NETWORKUP;
        measurementDTO.value = computeNetworkUpValue(measurement);
        return measurementDTO;
    }

    private int computeNetworkUpValue(Measurement measurement) {
        int networkId = measurement.getMeasurementPK().getNetworknetworkId();
        Network network = networkDao.findById(networkId);
        int networkUpValue = network.getNetworkUpload() != null 
                ? network.getNetworkUpload().intValue()
                : 0;
        return networkUpValue;
    }

    private MeasurementDTO convertNetworkDown(Measurement measurement) {
        MeasurementDTO measurementDTO = convertBasicData(measurement);
        measurementDTO.type = Type.NETWORKDOWN;
        measurementDTO.value = computeNetworkDownValue(measurement);
        return measurementDTO;
    }

    private int computeNetworkDownValue(Measurement measurement) {
        int networkId = measurement.getMeasurementPK().getNetworknetworkId();
        Network network = networkDao.findById(networkId);
        int networkDownValue = network.getNetworkDownload() != null 
                ? network.getNetworkDownload().intValue()
                : 0;
        return networkDownValue;
    }

    private MeasurementDTO convertMemory(Measurement measurement) {
        MeasurementDTO measurementDTO = convertBasicData(measurement);
        measurementDTO.type = Type.MEMORY;
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
        measurementDTO.type = CPU;
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
