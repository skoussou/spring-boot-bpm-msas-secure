package com.redhat.app.configuration.connections;

import java.util.Set;

import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.ProcessServicesClient;
import org.kie.server.client.QueryServicesClient;
import org.kie.server.client.RuleServicesClient;
import org.kie.server.client.UserTaskServicesClient;
import org.kie.server.client.admin.ProcessAdminServicesClient;
import org.kie.server.client.DMNServicesClient;

import org.kie.server.client.CredentialsProvider;
import org.kie.server.client.credentials.EnteredTokenCredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Bootstap Client for RHPAM Spring Boot services */
	public class AbstractKieServerConnector {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractKieServerConnector.class);

	private Long timeout = 5000L;
	private String url;
	private String username;
	private String pwd;
	private KieServicesClient client;
	private String ACCESS_TOKEN;
	private final long MAX_TIMEOUT = 15000L;

  private final String TOKEN_AUTH_PREFIX = "bearer ";

	public AbstractKieServerConnector() {

		// KIE SERVER
		KieServicesConfiguration config = KieServicesFactory
				.newRestConfiguration("http://localhost:8180/kie-server/services/rest/server", "pamAdmin", "pamAdmin", MAX_TIMEOUT);

		config.setMarshallingFormat(MarshallingFormat.JSON);
		this.client = KieServicesFactory.newKieServicesClient(config);				
	}

	public AbstractKieServerConnector(String serviceURL) {

		LOGGER.debug("Creating KieServicesConfiguration with it to serviceURL: ["+serviceURL+"]\n");

  	    // SPRING BOOT KIE SERVER (username/password)
		//KieServicesConfiguration config = KieServicesFactory
		//.newRestConfiguration("http://localhost:8090/rest/server", "pamAdmin", "pamAdmin");

		KieServicesConfiguration config = KieServicesFactory.newRestConfiguration(serviceURL, "user", "user", MAX_TIMEOUT);
		
		config.setMarshallingFormat(MarshallingFormat.JSON);
		this.client = KieServicesFactory.newKieServicesClient(config);

	}

	/** With CredentialsProvider KieServicesFactory
	 * -----------------------------------------------
	 * public static KieServicesConfiguration newRestConfiguration( String serverUrl, CredentialsProvider credentialsProvider ) {
	 * return new KieServicesConfigurationImpl( serverUrl, credentialsProvider );
	 * 
	 * CLASS KieServerUtils
	 * -----------------------
	 * public static CredentialsProvider getCredentialsProvider() {
	 *   return new CredentialsProvider() {
	 * 	    KeyCloakTokenCredentialsProvider keyCloakProvider = new KeyCloakTokenCredentialsProvider();
	 * 		  SubjectCredentialsProvider subjectProvider = new SubjectCredentialsProvider();
	 * 
	 * 		@Override
	 *   	public String getHeaderName() {
	 * 				return javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
	 * 		}
	 * 
	 * 		@Override
	 * 		public String getAuthorization() {
	 * 				Principal principal = SecurityIntegrationFilter.getRequest().getUserPrincipal();
	 * 				if (principal instanceof KeycloakPrincipal) {
	 * 						return keyCloakProvider.getAuthorization();
	 * 				} else {
	 * 						return subjectProvider.getAuthorization();
	 * 				}
	 * 		}
	 * };
	 * }
	 * 
	 * public static CredentialsProvider getAdminCredentialsProvider() {
	 *   if (System.getProperty(KieServerConstants.CFG_KIE_TOKEN) != null) {
	 *       return new EnteredTokenCredentialsProvider(System.getProperty(KieServerConstants.CFG_KIE_TOKEN));
	 *   } else {
	 *       return new EnteredCredentialsProvider(System.getProperty(KieServerConstants.CFG_KIE_USER,
	 *                                              "kieserver"),  loadServerPassword());
	 *   }
 	 *  }
	 * 	
	 * SPRING BOOT KIE SERVER RHSSO Service Account
	 * ----------------------------------------------
	 * KieServicesConfiguration config = KieServicesFactory
	 * .newRestConfiguration("http://localhost:8090/rest/server", new RedHatSSOCredentialProvider());
	 * 
	 * EnteredTokenCredentialsProvider
	 * --------------------------------
	 *  ./org.drools-droolsjbpm-integration-7.39.0.Final-7.8.0/kie-server-parent/kie-server-remote/kie-server-client/src/main/java/org/kie/server/client/credentials/EnteredTokenCredentialsProvider.java
  **/
	public AbstractKieServerConnector(String requestToken, String serviceURL) {
		LOGGER.debug("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#");
		LOGGER.debug("Request Token was --> ["+requestToken+"]");
		this.ACCESS_TOKEN = requestToken.substring( 7 , requestToken.length());
		LOGGER.debug("Setting ACCESS_TOKEN Token to --> ["+	this.ACCESS_TOKEN	+"]");
		LOGGER.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#");
		
		LOGGER.debug("Creating KieServicesConfiguration with it to serviceURL: ["+serviceURL+"]\n");

		// SPRING BOOT KIE SERVER
		// KieServicesConfiguration config = KieServicesFactory.
		//                        newRestConfiguration("http://localhost:8090/rest/server", getRequestCredentialsProvider());
		KieServicesConfiguration config = KieServicesFactory.
		newRestConfiguration(serviceURL, getRequestCredentialsProvider(), MAX_TIMEOUT);
		config.setMarshallingFormat(MarshallingFormat.JSON);
		this.client = KieServicesFactory.newKieServicesClient(config);
	}	

	public CredentialsProvider getRequestCredentialsProvider() {
		return new EnteredTokenCredentialsProvider(this.ACCESS_TOKEN);
  }

	public ProcessAdminServicesClient getAdminClient() {

		return this.client.getServicesClient(ProcessAdminServicesClient.class);
	}

	public ProcessServicesClient getProcessClient() {

		return this.client.getServicesClient(ProcessServicesClient.class);
	}

	public UserTaskServicesClient getTaskClient() {

		return this.client.getServicesClient(UserTaskServicesClient.class);
	}

	public QueryServicesClient getQueryClient() {

		return this.client.getServicesClient(QueryServicesClient.class);
	}

	public RuleServicesClient getRuleClient() {

		return this.client.getServicesClient(RuleServicesClient.class);
	}

	public DMNServicesClient getDMNClient() {
		return this.client.getServicesClient(DMNServicesClient.class);
	}

	public KieServicesClient getServicesClient(){
		return this.client;
	}

	public AbstractKieServerConnector(String Url, String username, String password, Set<Class<?>> extraClassList,
			MarshallingFormat format) {

		this.url = Url;
		this.username = username;
		this.pwd = password;

		KieServicesConfiguration config = KieServicesFactory.newRestConfiguration(this.url, this.username, this.pwd);
		config.setTimeout(timeout);
		if (extraClassList != null) {

			config.addExtraClasses(extraClassList);
		}
		if (format == null) {

			config.setMarshallingFormat(MarshallingFormat.JSON);
		} else {

			config.setMarshallingFormat(format);
		}
		this.client = KieServicesFactory.newKieServicesClient(config);

	}

	public Long getTimeout() {
		return timeout;
	}

	public void setTimeout(Long timeout) {
		this.timeout = timeout;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public KieServicesClient getClient() {
		return client;
	}

	public void setClient(KieServicesClient client) {
		this.client = client;
	}

}
