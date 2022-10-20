package br.com.sge.interceptor;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.sge.modelo.Usuario;

public class Authorization implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent phaseEvent) {

		FacesContext context = phaseEvent.getFacesContext();
		String nomePagina = context.getViewRoot().getViewId();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuario.logado");
		if ("/index.xhtml".equals(nomePagina)) {
			return;
		}
		if (usuario != null) {
			return;
		}
		
		NavigationHandler handler = context.getApplication().getNavigationHandler();
		handler.handleNavigation(context, null, "/home?faces-redirect=true");
		context.renderResponse();
	}

	@Override
	public void beforePhase(PhaseEvent phaseEvent) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
}