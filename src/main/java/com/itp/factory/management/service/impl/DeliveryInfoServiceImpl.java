package com.itp.factory.management.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import com.itp.factory.management.base.MessagePropertyBase;
import com.itp.factory.management.core.LogginAuthentcation;
import com.itp.factory.management.domain.DeliveryInfo;
import com.itp.factory.management.exception.ValidateRecordException;
import com.itp.factory.management.repository.DeliveryInfoRepository;
import com.itp.factory.management.resource.DeliveryInfoAddResource;
import com.itp.factory.management.resource.DeliveryInfoUpdateResource;
import com.itp.factory.management.service.DeliveryInfoService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
/**
 *Delivery Info Service Impl
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   05-09-2020                            Anushka        Created
 *    
 ********************************************************************************************************
 */



@Component
@Transactional(rollbackFor=Exception.class)
public class DeliveryInfoServiceImpl extends MessagePropertyBase  implements DeliveryInfoService{

	@Autowired
	private DeliveryInfoRepository deliveryInfoRepository;
	
	@Override
	public List<DeliveryInfo> getAll() {
		return deliveryInfoRepository.findAll();
	}
	
	@Override
	public Optional<DeliveryInfo> getById(Long id) {
		
		Optional<DeliveryInfo> isDelivery = deliveryInfoRepository.findById(id);
		if (isDelivery.isPresent()) {
			return Optional.ofNullable(isDelivery.get());
		}
		else {
			return Optional.empty();
		}
	}

	@Override
	public DeliveryInfo addDelivery(DeliveryInfoAddResource deliveryInfoAddResource) {
		
		if (LogginAuthentcation.getInstance().getUserName()==null || LogginAuthentcation.getInstance().getUserName().isEmpty())
			throw new ValidateRecordException(getEnvironment().getProperty(COMMON_NOT_NULL), "username");
		
        DeliveryInfo delivery = new DeliveryInfo();
        delivery.setAmount(deliveryInfoAddResource.getAmount());
        delivery = deliveryInfoRepository.save(delivery);
    	return delivery;
	}

	@Override
	public DeliveryInfo updateDelivery(DeliveryInfoUpdateResource deliveryInfoUpdateResource) {
		
		
		if (LogginAuthentcation.getInstance().getUserName()==null || LogginAuthentcation.getInstance().getUserName().isEmpty())
			throw new ValidateRecordException(getEnvironment().getProperty(COMMON_NOT_NULL), "username");
		
		Optional<DeliveryInfo> isDelivery = deliveryInfoRepository.findById(Long.parseLong(deliveryInfoUpdateResource.getQty()));
		if (!isDelivery.isPresent()) 
			throw new ValidateRecordException(getEnvironment().getProperty(RECORD_NOT_FOUND), "message");
	
		
		DeliveryInfo deliveryInfo = isDelivery.get();
		deliveryInfo = deliveryInfoRepository.saveAndFlush(deliveryInfo);
    	return deliveryInfo;
	}

	@Override
	public void deleteDelivery(long id) {
		
		Optional<DeliveryInfo> isDelivery = deliveryInfoRepository.findById(id);
		if (!isDelivery.isPresent()) 
			throw new ValidateRecordException(getEnvironment().getProperty(RECORD_NOT_FOUND), "message");
		else
			deliveryInfoRepository.deleteById(id);
	}
	

	
	@Override
	public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
		
		String path = "C:\\Users\\Anushka\\Desktop\\DeliveryReports";
		List<DeliveryInfo> deliveryInfo = deliveryInfoRepository.findAll();
		
		//Load File and compile
		File file = ResourceUtils.getFile("classpath:DeliveryReport.xml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(deliveryInfo);	
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("Created by","Java thechie");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\DeliveryReport.html");
		}
		if(reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\DeliveryReport.pdh");
		}
		
		return "Report generated path:"+path;
		
	}
}
