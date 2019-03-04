package org.openmrs.module.southsudanemr.reporting.manager;

import org.apache.commons.io.IOUtils;
import org.openmrs.api.context.Context;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.ReportDesignResource;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.manager.ReportManagerUtil;
import org.openmrs.module.reporting.report.renderer.ExcelTemplateRenderer;
import org.openmrs.module.reporting.report.service.ReportService;
import org.openmrs.module.reporting.report.util.ReportUtil;
import org.openmrs.util.OpenmrsClassLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public abstract class SsEmrDataExportManager extends SsEmrReportManager {
	
	/** @return the uuid for the report design for exporting to Excel */
	public abstract String getExcelDesignUuid();
	
	@Override
	public List<ReportDesign> constructReportDesigns(ReportDefinition reportDefinition) {
		List<ReportDesign> l = new ArrayList<ReportDesign>();
		ReportDesign excelDesign = ReportManagerUtil.createExcelDesign(getExcelDesignUuid(), reportDefinition);
		l.add(excelDesign);
		return l;
	}
	
	protected ReportDesign createExcelTemplateDesign(String reportDesignUuid, ReportDefinition reportDefinition,
	        String templatePath) {
		String resourcePath = ReportUtil.getPackageAsPath(getClass()) + "/" + templatePath;
		return ReportManagerUtil.createExcelTemplateDesign(reportDesignUuid, reportDefinition, resourcePath);
	}
	
	/** @return a new ReportDesign for a standard Excel output */
	public ReportDesign createExcelDesignWithProperties(String reportDesignUuid, ReportDefinition reportDefinition,
	        Properties props) {
		ReportDesign design = ReportManagerUtil.createExcelDesign(reportDesignUuid, reportDefinition);
		design.setProperties(props);
		return design;
	}
	
	/**
	 * Auto generated method comment
	 * 
	 * @param reportDefinition the reportDesign to set
	 * @param resourceName
	 * @param reportDesignName
	 * @param properties
	 * @return
	 * @throws IOException
	 */
	public static ReportDesign createXlsReportDesign(ReportDefinition reportDefinition, String resourceName,
	        String reportDesignName, String excelDesignUuid, Map<? extends Object, ? extends Object> properties)
	        throws IOException {
		
		ReportService rs = Context.getService(ReportService.class);
		for (ReportDesign rdd : rs.getAllReportDesigns(false)) {
			if (reportDesignName.equals(rdd.getName())) {
				rs.purgeReportDesign(rdd);
			}
		}
		
		ReportDesignResource resource = new ReportDesignResource();
		resource.setName(resourceName);
		resource.setExtension("xls");
		InputStream is = OpenmrsClassLoader.getInstance().getResourceAsStream(resourceName);
		resource.setContents(IOUtils.toByteArray(is));
		final ReportDesign design = new ReportDesign();
		design.setName(reportDesignName);
		design.setReportDefinition(reportDefinition);
		design.setRendererType(ExcelTemplateRenderer.class);
		design.addResource(resource);
		if (properties != null) {
			design.getProperties().putAll(properties);
		}
		if (excelDesignUuid != null && excelDesignUuid.length() > 1) {
			design.setUuid(excelDesignUuid);
		}
		resource.setReportDesign(design);
		
		return design;
	}
}
