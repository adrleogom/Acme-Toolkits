/*
 * AuthenticatedAnnouncementListAllService.java
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.entities.patronage.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.helpers.CollectionHelper;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorPatronageReportListService implements AbstractListService<Inventor, PatronageReport> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorPatronageReportRepository repository;

	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;
		
		boolean result;
		int masterId;
		Patronage patronage;
		
		masterId = request.getModel().getInteger("masterId");
		patronage =this.repository.findOnePatronageById(masterId);
		result = patronage != null && request.isPrincipal(patronage.getInventor());
		return result;
	}


	@Override
	public Collection<PatronageReport> findMany(final Request<PatronageReport> request) {
		assert request != null;

		final Collection<PatronageReport> result;

		result = this.repository.findAllPatronageReportsByPatronageMasterId(request.getModel().getInteger("masterId"));

		return result;
	}
	
	@Override
	public void unbind (final Request<PatronageReport> request, final Collection<PatronageReport> entities, final Model model) {
		assert request != null;
		assert !CollectionHelper.someNull(entities);
		assert model != null;
		
		int masterId;
		
		
		masterId = request.getModel().getInteger("masterId");
		
		
		model.setAttribute("masterId", masterId);
		
		
	}

	
	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "sNumber", "creationMoment");
	}


}
