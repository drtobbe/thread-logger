package se.util.eif.logging;

import java.lang.reflect.Method;

/**
 * @author drtobbe
 */
public class EifMetaData implements Cloneable {
    private static final String SET = "set";
    private static final String EQ = "=";
    private static final String SEP = ",";
    private long auditSequenceNo = 0L;
    protected String businessFragmentInstanceId;
    protected String businessFragmentName;
    protected String businessObjectType;
    protected String businessPartInstanceId;
    protected String businessPartName;
    protected String businessProcessInstanceId;
    protected String businessProcessName;
    protected String businessTransactionDescription;
    protected String functionalModuleInstanceId;
    protected String functionalModuleName;
    protected String globalState;
    protected String hostname;
    protected String integrationIdentity;
    protected String keyFields;
    protected String messageHistory;
    protected String receiverInformation;
    protected String relatedDocumentId;
    protected String relatedDocumentLocation;
    protected String relatedDocumentType;
    protected String senderInformation;
    protected String sourceType;
    protected String systemName;
    protected String transactionIdentity;
    protected String userId;

    public long getAuditSequenceNoAndIncrement() {
        return (auditSequenceNo++);
    }

    public String getBusinessFragmentInstanceId() {
        return businessFragmentInstanceId;
    }

    public void setBusinessFragmentInstanceId(String businessFragmentInstanceId) {
        this.businessFragmentInstanceId = businessFragmentInstanceId;
    }

    public String getBusinessFragmentName() {
        return businessFragmentName;
    }

    public void setBusinessFragmentName(String businessFragmentName) {
        this.businessFragmentName = businessFragmentName;
    }

    public String getBusinessObjectType() {
        return businessObjectType;
    }

    public void setBusinessObjectType(String businessObjectType) {
        this.businessObjectType = businessObjectType;
    }

    public String getBusinessPartInstanceId() {
        return businessPartInstanceId;
    }

    public void setBusinessPartInstanceId(String businessPartInstanceId) {
        this.businessPartInstanceId = businessPartInstanceId;
    }

    public String getBusinessPartName() {
        return businessPartName;
    }

    public void setBusinessPartName(String businessPartName) {
        this.businessPartName = businessPartName;
    }

    public String getBusinessProcessInstanceId() {
        return businessProcessInstanceId;
    }

    public void setBusinessProcessInstanceId(String businessProcessInstanceId) {
        this.businessProcessInstanceId = businessProcessInstanceId;
    }

    public String getBusinessProcessName() {
        return businessProcessName;
    }

    public void setBusinessProcessName(String businessProcessName) {
        this.businessProcessName = businessProcessName;
    }

    public String getBusinessTransactionDescription() {
        return businessTransactionDescription;
    }

    public void setBusinessTransactionDescription(String businessTransactionDescription) {
        this.businessTransactionDescription = businessTransactionDescription;
    }

    public String getFunctionalModuleInstanceId() {
        return functionalModuleInstanceId;
    }

    public void setFunctionalModuleInstanceId(String functionalModuleInstanceId) {
        this.functionalModuleInstanceId = functionalModuleInstanceId;
    }

    public String getFunctionalModuleName() {
        return functionalModuleName;
    }

    public void setFunctionalModuleName(String functionalModuleName) {
        this.functionalModuleName = functionalModuleName;
    }

    public String getGlobalState() {
        return globalState;
    }

    public void setGlobalState(String globalState) {
        this.globalState = globalState;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIntegrationIdentity() {
        return integrationIdentity;
    }

    public void setIntegrationIdentity(String integrationIdentity) {
        this.integrationIdentity = integrationIdentity;
    }

    public String getKeyFields() {
        return keyFields;
    }

    public void setKeyFields(String keyFields) {
        this.keyFields = keyFields;
    }

    public String getMessageHistory() {
        return messageHistory;
    }

    public void setMessageHistory(String messageHistory) {
        this.messageHistory = messageHistory;
    }

    public String getReceiverInformation() {
        return receiverInformation;
    }

    public void setReceiverInformation(String receiverInformation) {
        this.receiverInformation = receiverInformation;
    }

    public String getRelatedDocumentId() {
        return relatedDocumentId;
    }

    public void setRelatedDocumentId(String relatedDocumentId) {
        this.relatedDocumentId = relatedDocumentId;
    }

    public String getRelatedDocumentLocation() {
        return relatedDocumentLocation;
    }

    public void setRelatedDocumentLocation(String relatedDocumentLocation) {
        this.relatedDocumentLocation = relatedDocumentLocation;
    }

    public String getRelatedDocumentType() {
        return relatedDocumentType;
    }

    public void setRelatedDocumentType(String relatedDocumentType) {
        this.relatedDocumentType = relatedDocumentType;
    }

    public String getSenderInformation() {
        return senderInformation;
    }

    public void setSenderInformation(String senderInformation) {
        this.senderInformation = senderInformation;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getTransactionIdentity() {
        return transactionIdentity;
    }

    public void setTransactionIdentity(String transactionIdentity) {
        this.transactionIdentity = transactionIdentity;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    protected synchronized EifMetaData clone() {
        EifMetaData clone = new EifMetaData();
        clone.setBusinessFragmentInstanceId(businessFragmentInstanceId);
        clone.setBusinessFragmentName(businessFragmentName);
        clone.setBusinessObjectType(businessObjectType);
        clone.setBusinessPartInstanceId(businessPartInstanceId);
        clone.setBusinessPartName(businessPartName);
        clone.setBusinessProcessInstanceId(businessProcessInstanceId);
        clone.setBusinessProcessName(businessProcessName);
        clone.setBusinessTransactionDescription(businessTransactionDescription);
        clone.setFunctionalModuleInstanceId(functionalModuleInstanceId);
        clone.setFunctionalModuleName(functionalModuleName);
        clone.setGlobalState(globalState);
        clone.setHostname(hostname);
        clone.setIntegrationIdentity(integrationIdentity);
        clone.setKeyFields(keyFields);
        clone.setMessageHistory(messageHistory);
        clone.setReceiverInformation(receiverInformation);
        clone.setRelatedDocumentId(relatedDocumentId);
        clone.setRelatedDocumentLocation(relatedDocumentLocation);
        clone.setRelatedDocumentType(relatedDocumentType);
        clone.setSenderInformation(senderInformation);
        clone.setSourceType(sourceType);
        clone.setSystemName(systemName);
        clone.setTransactionIdentity(transactionIdentity);
        clone.setUserId(userId);
        return clone;

    }

    /**
     * Setup EifMetaData with aid of comma separted string with properties, e.g.
     * String props = "businessProcessName=BusProc, businessPartName=BusPart, businessFragmentName=FraName, "
     *   + "businessFragmentInstanceId=FraIns, systemName=SysNam, functionalModuleName=FunModNam, functionalModuleInstanceId=FunModInsId";
     */
    public void setupPropertiesFromString(String props) {
        try {
            if (props != null && props.length() > 3) {
                String[] split = props.split(SEP);
                for (int i = 0; i < split.length; i++) {
                    String keyVal = split[i].trim();
                    if (keyVal.contains(EQ)) {
                        int indexOf = keyVal.indexOf(EQ);
                        String key = keyVal.substring(0, indexOf);
                        String method = SET + key.substring(0, 1).toUpperCase() + key.substring(1, key.length());
                        String val = keyVal.substring(indexOf + 1, keyVal.length());
                        setString(method, val);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to parse String: " + props);
            e.printStackTrace();
        }
    }

    private void setString(String methodname, String value) throws Exception {
        Class<?>[] parameterTypes = { String.class };
        Method method = this.getClass().getMethod(methodname, parameterTypes);
        Object[] args = { value };
        method.invoke(this, args);
    }

}
