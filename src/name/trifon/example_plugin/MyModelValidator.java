package name.trifon.example_plugin;

import org.compiere.model.MClient;
import org.compiere.model.MOrder;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.CLogger;

public class MyModelValidator implements ModelValidator {

	private static CLogger LOG = CLogger.getCLogger(MyModelValidator.class);

	private int clientId = -1;

	@SuppressWarnings("unused")
	private int orgId = -1;

	@SuppressWarnings("unused")
	private int roleId = -1;

	@SuppressWarnings("unused")
	private int userId = -1;


	@Override
	public void initialize(ModelValidationEngine modelValidationEngine, MClient client) {
		if (client != null) 
			clientId = client.getAD_Client_ID();

		// Register to listen for Model changes of:
		modelValidationEngine.addModelChange(MOrder.Table_Name, this);
		LOG.info("Trifon - Registerd for MOrder changes.");
		System.out.println("Trifon - Registerd for MOrder changes.");
	}

	@Override
	public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) {
		orgId = AD_Org_ID;
		roleId = AD_Role_ID;
		userId = AD_User_ID;

		return null;
	}

	@Override
	public int getAD_Client_ID() {
		return clientId;
	}

	@Override
	public String modelChange(PO po, int type) throws Exception {
		LOG.info("TRIFON - modelChange - " +po.toString() + "; type=" + type);
		System.out.println("TRIFON - modelChange - " +po.toString() + "; type=" + type);
		return null;
	}

	@Override
	public String docValidate(PO po, int timing) {
		LOG.info("TRIFON - docValidate - " +po.toString() + "; timing=" + timing);
		System.out.println("TRIFON - modelChange - " +po.toString() + "; timing=" + timing);

		return null;
	}
}