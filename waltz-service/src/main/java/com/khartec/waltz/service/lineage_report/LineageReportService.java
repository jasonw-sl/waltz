package com.khartec.waltz.service.lineage_report;

import com.khartec.waltz.data.lineage_report.LineageReportDao;
import com.khartec.waltz.model.lineage_report.CreateLineageReportCommand;
import com.khartec.waltz.model.lineage_report.LineageReport;
import com.khartec.waltz.model.lineage_report.LineageReportDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dwatkins on 12/10/2016.
 */
@Service
public class LineageReportService {

    private static final Logger LOG = LoggerFactory.getLogger(LineageReportService.class);

    private final LineageReportDao lineageReportDao;


    @Autowired
    public LineageReportService(LineageReportDao lineageReportDao) {
        this.lineageReportDao = lineageReportDao;
    }


    public LineageReport getById(long id) {
        return lineageReportDao.getById(id);
    }


    public List<LineageReport> findByPhysicalArticleId(long physicalArticleId) {
        return lineageReportDao.findByPhysicalArticleId(physicalArticleId);
    }


    public List<LineageReportDescriptor> findReportsContributedToByArticle(long physicalArticleId) {
        return lineageReportDao.findReportsContributedToByArticle(physicalArticleId);
    }


    public long create(CreateLineageReportCommand command, String username) {
        LOG.info("Creating lineage report: {} for {}", command, username);
        return lineageReportDao.create(command, username);
    }
}