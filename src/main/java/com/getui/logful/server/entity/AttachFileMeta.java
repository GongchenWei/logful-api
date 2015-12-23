package com.getui.logful.server.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "attach_file_meta")
public class AttachFileMeta {

    @Id
    private String id;

    private String fid;

    private String attachmentId;

    private Date writeDate;

    private long size;

    public static AttachFileMeta create(String attachmentId) {
        AttachFileMeta meta = new AttachFileMeta();
        meta.setAttachmentId(attachmentId);
        meta.setWriteDate(new Date());
        return meta;
    }

    public Date getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Date writeDate) {
        this.writeDate = writeDate;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

}