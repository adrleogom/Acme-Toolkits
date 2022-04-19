/*
 * AuthenticatedAnnouncementRepository.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.inventor.patronageReport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronage.PatronageReport;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorPatronageReportRepository extends AbstractRepository {


	@Query("select a from PatronageReport a")
	Collection<PatronageReport> findAllPatronageReports();

//	@Query("select a from Announcement a where a.moment > :deadline")
//	Collection<Announcement> findRecentAnnouncements(Date deadline);
	
	@Query("select a from PatronageReport a where a.id = :masterId")
	PatronageReport findOnePatronageReportById (int masterId);
	
	@Query("select a from PatronageReport a where a.patronage.id = :masterId")
	Collection<PatronageReport> findManyPatronageReportsByMasterId(int masterId);
	
//	@Query("select p from Patronage p where p.id = :masterId")
//	Patronage findOnePatronageById(int masterId);
//
//	@Query("select d.job from Duty d where d.id = :masterId")
//	Job findOneJobByDutyId(int masterId);
//
//	@Query("select d from Duty d where d.id = :id")
//	Duty findOneDutyById(int id);
//
//	@Query("select d from Duty d where d.job.id = :masterId")
//	Collection<Duty> findManyDutiesByMasterId(int masterId);

}