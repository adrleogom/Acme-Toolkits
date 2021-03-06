package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.HttpMethod;
import acme.framework.controllers.Request;
import acme.framework.controllers.Response;
import acme.framework.entities.Principal;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitPublishService implements AbstractUpdateService<Inventor, Toolkit> {

	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorToolkitRepository repository;
	
	// AbstractUpdateService<Authenticated, Toolkit> interface ---------------
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request !=null;
		
		final boolean result;
		int masterId;
		Toolkit toolkit;
		int userAccountId;
		Principal principal;
		Inventor inventor;

		
		masterId = request.getModel().getInteger("id");
		toolkit = this.repository.findOneToolkitById(masterId);
		
		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		inventor = this.repository.findOneInventorByUserAccountId(userAccountId);
		
		result = toolkit.getInventor().getId() == inventor.getId() && !toolkit.isPublished();
		
		return result;
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "title", "description", "assemblyNotes", "furtherInfo", "code");
		
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "assemblyNotes", "furtherInfo", "code");
		
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;
		
		Toolkit result;
		int masterId;

		masterId = request.getModel().getInteger("id");
		result = this.repository.findOneToolkitById(masterId);
		return result;
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		Collection<Item> items;
		
		items = this.repository.findManyItemsByToolkitId(entity.getId());
		Boolean published = true;
		
		if (!errors.hasErrors("emptyItems")) {
			errors.state(request, items!=null && !items.isEmpty() , "*", "inventor.toolkit.form.error.empty-items");
		}
		
		for (final Item item : items) {
			published = published && item.isPublished();
		}
		
		if (!errors.hasErrors("itemNoPublished")) {
			errors.state(request, published , "*", "inventor.toolkit.form.error.item-no-published");
		}
		
		
	}

	@Override
	public void update(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;
		
		entity.setPublished(true);
		this.repository.save(entity);
		
	}
	
	@Override
	public void onSuccess(final Request<Toolkit> request, final Response<Toolkit> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}
}
