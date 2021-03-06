package com.getui.logful.server.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.getui.logful.server.util.RSAUtil;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.*;

@Document(collection = "oauth2_client")
public class SimpleClientDetails {

    @Id
    private String id;

    private String name;
    private String description;
    private String packageName;
    private String bundleId;
    private String getuiAppId;
    private String getuiAppKey;
    private String getuiMasterSecret;
    private Date createDate;
    private Date updateDate;
    @Indexed
    private String clientId;
    @JsonIgnore
    private Set<String> resourceIds = new HashSet<>();
    @JsonIgnore
    private boolean secretRequired;
    @Indexed
    private String clientSecret;
    @JsonIgnore
    private boolean scoped;
    @JsonIgnore
    private Set<String> scope = new HashSet<>();
    @JsonIgnore
    private Set<String> authorizedGrantTypes = new HashSet<>();
    @JsonIgnore
    private Set<String> registeredRedirectUri = new HashSet<>();
    @JsonIgnore
    private Collection<String> authorities = new LinkedHashSet<>();
    @JsonIgnore
    private Integer accessTokenValiditySeconds;
    @JsonIgnore
    private Integer refreshTokenValiditySeconds;
    @JsonIgnore
    private boolean autoApprove;
    @JsonIgnore
    private Map<String, Object> additionalInformation = new LinkedHashMap<>();

    public SimpleClientDetails() {
        BaseClientDetails temp = new BaseClientDetails();
        this.setAccessTokenValiditySeconds(temp.getAccessTokenValiditySeconds());
        this.setRefreshTokenValiditySeconds(temp.getRefreshTokenValiditySeconds());
    }

    public SimpleClientDetails(ClientDetails clientDetails) {
        this.accessTokenValiditySeconds = clientDetails.getAccessTokenValiditySeconds();
        this.additionalInformation = clientDetails.getAdditionalInformation();
        this.authorizedGrantTypes = clientDetails.getAuthorizedGrantTypes();
        this.clientId = clientDetails.getClientId();
        this.clientSecret = clientDetails.getClientSecret();
        this.refreshTokenValiditySeconds = clientDetails.getRefreshTokenValiditySeconds();
        this.registeredRedirectUri = clientDetails.getRegisteredRedirectUri();
        this.resourceIds = clientDetails.getResourceIds();
        this.scope = clientDetails.getScope();
        this.scoped = clientDetails.isScoped();
        this.secretRequired = clientDetails.isSecretRequired();
    }

    public String getBundleId() {
        return bundleId;
    }

    public void setBundleId(String bundleId) {
        this.bundleId = bundleId;
    }

    public String getGetuiAppId() {
        return getuiAppId;
    }

    public void setGetuiAppId(String getuiAppId) {
        this.getuiAppId = getuiAppId;
    }

    public String getGetuiMasterSecret() {
        return getuiMasterSecret;
    }

    public void setGetuiMasterSecret(String getuiMasterSecret) {
        this.getuiMasterSecret = getuiMasterSecret;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getGetuiAppKey() {
        return getuiAppKey;
    }

    public void setGetuiAppKey(String getuiAppKey) {
        this.getuiAppKey = getuiAppKey;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void authorities(String... authorities) {
        Collections.addAll(this.authorities, authorities);
    }

    public void authorizedGrantTypes(String... authorizedGrantTypes) {
        Collections.addAll(this.authorizedGrantTypes, authorizedGrantTypes);
    }

    public void scopes(String... scopes) {
        Collections.addAll(this.scope, scopes);
    }

    public void addKeyPair(KeyPair keyPair) {
        additionalInformation.put("public", keyPair.getPublic().getEncoded());
        additionalInformation.put("private", keyPair.getPrivate().getEncoded());
    }

    public KeyPair keyPair() {
        try {
            PublicKey publicKey = RSAUtil.publicKey(additionalInformation.get("public"));
            PrivateKey privateKey = RSAUtil.privateKey(additionalInformation.get("private"));
            return new KeyPair(publicKey, privateKey);
        } catch (Exception e) {
            return null;
        }
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Set<String> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(Set<String> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public boolean isSecretRequired() {
        return secretRequired;
    }

    public void setSecretRequired(boolean secretRequired) {
        this.secretRequired = secretRequired;
    }

    public boolean isScoped() {
        return scoped;
    }

    public void setScoped(boolean scoped) {
        this.scoped = scoped;
    }

    public Set<String> getScope() {
        return scope;
    }

    public void setScope(Set<String> scope) {
        this.scope = scope;
    }

    public Set<String> getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(Set<String> authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public Set<String> getRegisteredRedirectUri() {
        return registeredRedirectUri;
    }

    public void setRegisteredRedirectUri(Set<String> registeredRedirectUri) {
        this.registeredRedirectUri = registeredRedirectUri;
    }

    public Collection<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<String> authorities) {
        this.authorities = authorities;
    }

    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

    public boolean isAutoApprove() {
        return autoApprove;
    }

    public void setAutoApprove(boolean autoApprove) {
        this.autoApprove = autoApprove;
    }

    public Map<String, Object> getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(Map<String, Object> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
}
