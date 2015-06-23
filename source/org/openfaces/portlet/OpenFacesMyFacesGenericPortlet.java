/*
 * OpenFaces - JSF Component Library 2.0
 * Copyright (C) 2007-2010, TeamDev Ltd.
 * licensing@openfaces.org
 * Unless agreed in writing the contents of this file are subject to
 * the GNU Lesser General Public License Version 2.1 (the "LGPL" License).
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * Please visit http://openfaces.org/licensing/ for more details.
 */
package org.openfaces.portlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.config.FacesConfigurator;
import org.apache.myfaces.context.ReleaseableExternalContext;
import org.apache.myfaces.context.portlet.PortletExternalContextImpl;
import org.apache.myfaces.context.servlet.ServletFacesContextImpl;
import org.apache.myfaces.portlet.DefaultViewSelector;
import org.apache.myfaces.portlet.MyFacesGenericPortlet;
import org.apache.myfaces.portlet.PortletUtil;
import org.apache.myfaces.shared_impl.webapp.webxml.WebXml;

import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.ApplicationFactory;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextFactory;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;
import javax.faces.webapp.FacesServlet;
import javax.portlet.*;
import java.io.IOException;

/**
 * @author Eugene Goncharov
 */
public class OpenFacesMyFacesGenericPortlet extends GenericPortlet {
    private static final Log log = LogFactory.getLog(MyFacesGenericPortlet.class);

    // PortletRequest parameter
    public static final String VIEW_ID =
            MyFacesGenericPortlet.class.getName() + ".VIEW_ID";

    // PortletSession attribute
    protected static final String CURRENT_FACES_CONTEXT =
            MyFacesGenericPortlet.class.getName() + ".CURRENT_FACES_CONTEXT";

    // portlet config parameter from portlet.xml
    protected static final String DEFAULT_VIEW = "default-view";

    // portlet config parameter from portlet.xml
    protected static final String DEFAULT_VIEW_SELECTOR = "default-view-selector";

    protected static final String FACES_INIT_DONE =
            MyFacesGenericPortlet.class.getName() + ".FACES_INIT_DONE";

    protected PortletContext portletContext;

    protected FacesContextFactory facesContextFactory;
    protected Lifecycle lifecycle;

    protected String defaultView;
    protected DefaultViewSelector defaultViewSelector;

    /**
     * Creates a new instance of MyFacesPortlet
     */
    public OpenFacesMyFacesGenericPortlet() {
    }

    /**
     * Portlet lifecycle.
     */
    public void destroy() {
        super.destroy();
        FactoryFinder.releaseFactories();
    }

    /**
     * Portlet lifecycle.
     */
    public void init() throws PortletException, UnavailableException {
        this.portletContext = getPortletContext();
        setDefaultView();
        setDefaultViewSelector();
        initMyFaces();

        facesContextFactory = (FacesContextFactory) FactoryFinder.getFactory(FactoryFinder.FACES_CONTEXT_FACTORY);

        // Javadoc says: Lifecycle instance is shared across multiple simultaneous requests, it must be
        // implemented in a thread-safe manner.  So we can acquire it here once:
        LifecycleFactory lifecycleFactory = (LifecycleFactory) FactoryFinder.getFactory(FactoryFinder.LIFECYCLE_FACTORY);
        lifecycle = lifecycleFactory.getLifecycle(getLifecycleId());
    }

    protected void setDefaultView() throws UnavailableException {
        this.defaultView = getPortletConfig().getInitParameter(DEFAULT_VIEW);
        if (defaultView == null) {
            String msg = "Fatal: must specify a JSF view id as the default view in portlet.xml";
            throw new UnavailableException(msg);
        }
    }

    protected void setDefaultViewSelector() throws UnavailableException {
        String selectorClass = getPortletConfig().getInitParameter(DEFAULT_VIEW_SELECTOR);
        if (selectorClass == null) return;

        try {
            this.defaultViewSelector = (DefaultViewSelector) Class.forName(selectorClass).newInstance();
            this.defaultViewSelector.setPortletContext(getPortletContext());
        }
        catch (Exception e) {
            log.error("Failed to load " + DEFAULT_VIEW_SELECTOR, e);
            throw new UnavailableException(e.getMessage());
        }
    }

    protected void setContentType(RenderRequest request, RenderResponse response) {

        if (response.getContentType() == null) {
            String portalPreferredContentType = request.getResponseContentType();
            if (portalPreferredContentType != null) {
                response.setContentType(portalPreferredContentType);
            } else {
                response.setContentType("text/html");
            }
        }
    }

    protected String getLifecycleId() {
        String lifecycleId = getPortletConfig().getInitParameter(FacesServlet.LIFECYCLE_ID_ATTR);
        return lifecycleId != null ? lifecycleId : LifecycleFactory.DEFAULT_LIFECYCLE;
    }

    protected void initMyFaces() {
        try {
            Boolean b = (Boolean) portletContext.getAttribute(FACES_INIT_DONE);

            if (b == null || b.booleanValue() == false) {
                log.trace("Initializing MyFaces");

                //Load the configuration
                ExternalContext externalContext = new PortletExternalContextImpl(portletContext, null, null);

                //And configure everything
                new FacesConfigurator(externalContext).configure();

                // parse web.xml - not sure if this is needed for portlet
                WebXml.init(externalContext);

                portletContext.setAttribute(FACES_INIT_DONE, Boolean.TRUE);
            } else {
                log.info("MyFaces already initialized");
            }
        }
        catch (Exception ex) {
            log.error("Error initializing MyFacesGenericPortlet", ex);
        }

        log.info("PortletContext '" + portletContext.getRealPath("/") + "' initialized.");
    }

    /**
     * Called by the portlet container to allow the portlet to process an action request.
     */
    public void processAction(ActionRequest request, ActionResponse response)
            throws PortletException, IOException {
        if (log.isTraceEnabled()) log.trace("called processAction");

//        if (sessionTimedOut(request)) return;
        boolean isSessionTimeOut = sessionTimedOut(request);

        setPortletRequestFlag(request);

        ServletFacesContextImpl facesContext = (ServletFacesContextImpl) facesContext(request, response);

        if (isSessionTimeOut) {
            ApplicationFactory appFactory =
                    (ApplicationFactory) FactoryFinder.getFactory(FactoryFinder.APPLICATION_FACTORY);
            Application application = appFactory.getApplication();
            ViewHandler viewHandler = application.getViewHandler();
            String viewId = (String) facesContext.getExternalContext().getRequestParameterMap().get("jsf_viewid");
            UIViewRoot viewRoot = viewHandler.createView(facesContext, viewId);
            viewRoot.setViewId(viewId);
            facesContext.setViewRoot(viewRoot);

            if (!facesContext.getResponseComplete()) {
                response.setRenderParameter(VIEW_ID, viewId);
            }

            try {
                if (facesContext.getResponseComplete()) return;
                facesContext.setExternalContext(makeExternalContext(request, response));
                request.getPortletSession().setAttribute(CURRENT_FACES_CONTEXT, facesContext);

            }
            catch (Throwable e) {
                handleExceptionFromLifecycle(e);
            }

        } else {
            try {
                lifecycle.execute(facesContext);

                if (!facesContext.getResponseComplete()) {
                    response.setRenderParameter(VIEW_ID, facesContext.getViewRoot().getViewId());
                }

                request.getPortletSession().setAttribute(CURRENT_FACES_CONTEXT, facesContext);
            }
            catch (Throwable e) {
                facesContext.release();
                handleExceptionFromLifecycle(e);
            }
        }
    }

    protected void handleExceptionFromLifecycle(Throwable e)
            throws PortletException, IOException {
        logException(e, null);

        if (e instanceof IOException) {
            throw (IOException) e;
        }

        if (e instanceof PortletException) {
            throw (PortletException) e;
        }

        if (e.getMessage() != null) {
            throw new PortletException(e.getMessage(), e);
        }

        throw new PortletException(e);
    }

    /**
     * Helper method to serve up the view mode.
     */
    protected void doView(RenderRequest request, RenderResponse response)
            throws PortletException, IOException {
        facesRender(request, response);
    }

    /**
     * Helper method to serve up the edit mode.  Can be overridden to add
     * the edit mode concept to a JSF application.
     */
    protected void doEdit(RenderRequest request, RenderResponse response)
            throws PortletException, IOException {
        facesRender(request, response);
    }

    /**
     * Helper method to serve up the edit mode.  Can be overridden to add
     * the help mode concept to a JSF application.
     */
    protected void doHelp(RenderRequest request, RenderResponse response)
            throws PortletException, IOException {
        facesRender(request, response);
    }

    /**
     * This method follows JSF Spec section 2.1.1.  It renders the default view from a non-faces
     * request.
     *
     * @param request  The portlet render request.
     * @param response The portlet render response.
     */
    protected void nonFacesRequest(RenderRequest request, RenderResponse response) throws PortletException {
        nonFacesRequest(request, response, selectDefaultView(request, response));
    }

    /**
     * This method follows JSF Spec section 2.1.1.  It renders a view from a non-faces
     * request.  This is useful for a default view as well as for views that need to
     * be rendered from the portlet's edit and help buttons.
     *
     * @param request  The portlet render request.
     * @param response The portlet render response.
     * @param view     The name of the view that needs to be rendered.
     */
    protected void nonFacesRequest(RenderRequest request, RenderResponse response, String view)
            throws PortletException {
        if (log.isTraceEnabled()) log.trace("Non-faces request: contextPath = " + request.getContextPath());
        setContentType(request, response); // do this in case nonFacesRequest is called by a subclass
        ApplicationFactory appFactory =
                (ApplicationFactory) FactoryFinder.getFactory(FactoryFinder.APPLICATION_FACTORY);
        Application application = appFactory.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        FacesContext facesContext = facesContext(request, response);
        UIViewRoot viewRoot = viewHandler.createView(facesContext, view);
        viewRoot.setViewId(view);
        facesContext.setViewRoot(viewRoot);
        lifecycle.render(facesContext);
    }

    protected String selectDefaultView(RenderRequest request, RenderResponse response) throws PortletException {
        String view = this.defaultView;
        if (this.defaultViewSelector != null) {
            String selectedView = this.defaultViewSelector.selectViewId(request, response);
            if (selectedView != null) {
                view = selectedView;
            }
        }

        return view;
    }

    protected FacesContext facesContext(PortletRequest request,
                                        PortletResponse response) {
        return facesContextFactory.getFacesContext(portletContext,
                request,
                response,
                lifecycle);
    }

    protected ReleaseableExternalContext makeExternalContext(PortletRequest request,
                                                             PortletResponse response) {
        return (ReleaseableExternalContext) new PortletExternalContextImpl(portletContext, request, response);
    }

    protected boolean sessionTimedOut(PortletRequest request) {
        return request.getPortletSession(false) == null;
    }

    protected void setPortletRequestFlag(PortletRequest request) {
        request.getPortletSession().setAttribute(PortletUtil.PORTLET_REQUEST_FLAG, "true");
    }

    /**
     * Render a JSF view.
     */
    protected void facesRender(RenderRequest request, RenderResponse response)
            throws PortletException, java.io.IOException {
        if (log.isTraceEnabled()) log.trace("called facesRender");

        setContentType(request, response);

        String viewId = request.getParameter(VIEW_ID);
        if ((viewId == null) || sessionTimedOut(request)) {
            setPortletRequestFlag(request);
            nonFacesRequest(request, response);
            return;
        }

        setPortletRequestFlag(request);

        try {
            ServletFacesContextImpl facesContext = (ServletFacesContextImpl) request.
                    getPortletSession().
                    getAttribute(CURRENT_FACES_CONTEXT);

            // TODO: not sure if this can happen.  Also double check this against spec section 2.1.3
            if (facesContext.getResponseComplete()) return;

            facesContext.setExternalContext(makeExternalContext(request, response));
            lifecycle.render(facesContext);
        }
        catch (Throwable e) {
            handleExceptionFromLifecycle(e);
        }
    }

    protected void logException(Throwable e, String msgPrefix) {
        String msg;
        if (msgPrefix == null) {
            if (e.getMessage() == null) {
                msg = "Exception in FacesServlet";
            } else {
                msg = e.getMessage();
            }
        } else {
            if (e.getMessage() == null) {
                msg = msgPrefix;
            } else {
                msg = msgPrefix + ": " + e.getMessage();
            }
        }

        portletContext.log(msg, e);

        Throwable cause = e.getCause();
        if (cause != null && cause != e) {
            logException(cause, "Root cause");
        }

        if (e instanceof PortletException) {
            cause = ((PortletException) e).getCause();

            if (cause != null && cause != e) {
                logException(cause, "Root cause of PortletException");
            }
        }
    }

}