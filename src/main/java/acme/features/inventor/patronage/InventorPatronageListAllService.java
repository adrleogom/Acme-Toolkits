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

package acme.features.inventor.patronage;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorPatronageListAllService implements AbstractListService<Inventor, Patronage> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorPatronageRepository repository;

	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Patronage> findMany(final Request<Patronage> request) {
		assert request != null;

		Collection<Patronage> result;
		Principal principal;
		principal = request.getPrincipal();
		
		result = this.repository.findAllPatronagesOfInventor(principal.getAccountId());
		
	//	result = this.repository.findAllPatronages();

		return result;
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "status", "code", "initialDate", "finalDate");
		
	}

	
	// AbstractListService<Administrator, Announcement> interface --------------


//	@Override
//	public boolean authorise(final Request<Announcement> request) {
//		assert request != null;
//
//		return true;
//	}
//
//	@Override
//	public Collection<Announcement> findMany(final Request<Announcement> request) {
//		assert request != null;
//
//		Collection<Announcement> result;
//
//		result = this.repository.findAllAnnouncements();
//
//		return result;
//	}
//	
//	@Override
//	public void unbind(final Request<Announcement> request, final Announcement entity, final Model model) {
//		assert request != null;
//		assert entity != null;
//		assert model != null;
//
//		request.unbind(entity, model, "title", "moment", "status");
//	}

}
