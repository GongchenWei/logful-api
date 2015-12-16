package com.getui.logful.server.rest;

import com.getui.logful.server.util.DateTimeUtil;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

public class BaseRestController {

    private static final int DEFAULT_LIMIT = 20;

    private static final int DEFAULT_OFFSET = 0;

    public String ok() {
        JSONObject object = new JSONObject();
        object.put("status", "200 OK");
        return object.toString();
    }

    public String deleted() {
        JSONObject object = new JSONObject();
        object.put("status", "204 DELETED");
        return object.toString();
    }

    public String created() {
        JSONObject object = new JSONObject();
        object.put("status", "201 Created");
        return object.toString();
    }

    public String updated() {
        JSONObject object = new JSONObject();
        object.put("updatedAt", DateTimeUtil.timeString(System.currentTimeMillis()));
        return object.toString();
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public class BadRequestException extends RuntimeException {
        public BadRequestException() {
            super();
        }

        public BadRequestException(String message) {
            super(message);
        }
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public class ForbiddenException extends RuntimeException {
        public ForbiddenException() {
            super();
        }

        public ForbiddenException(String message) {
            super(message);
        }
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public class NotFoundException extends RuntimeException {
        public NotFoundException() {
            super();
        }

        public NotFoundException(String message) {
            super(message);
        }
    }

    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public class NotAcceptableException extends RuntimeException {
        public NotAcceptableException() {
            super();
        }

        public NotAcceptableException(String message) {
            super(message);
        }
    }

    @ResponseStatus(value = HttpStatus.GONE)
    public class GoneException extends RuntimeException {
        public GoneException() {
            super();
        }

        public GoneException(String message) {
            super(message);
        }
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public class UnprocessableEntityException extends RuntimeException {
        public UnprocessableEntityException() {
            super();
        }

        public UnprocessableEntityException(String message) {
            super(message);
        }
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public class InternalServerException extends RuntimeException {
        public InternalServerException() {
            super();
        }

        public InternalServerException(String message) {
            super(message);
        }
    }

    public class QueryCondition {

        private Sort.Direction order;

        private String sort;

        private int limit;

        private int offset;

        public QueryCondition(WebRequest request) {
            String sort = request.getParameter("sort");
            String order = request.getParameter("order");
            String limit = request.getParameter("limit");
            String offset = request.getParameter("offset");

            if (StringUtils.isEmpty(sort)) {
                this.sort = "_id";
            } else {
                this.sort = sort;
            }

            if (StringUtils.isEmpty(order)) {
                this.order = Sort.Direction.ASC;
            } else {
                if (StringUtils.equalsIgnoreCase(order, "asc")) {
                    this.order = Sort.Direction.ASC;
                } else if (StringUtils.equalsIgnoreCase(order, "desc")) {
                    this.order = Sort.Direction.DESC;
                } else {
                    this.order = Sort.Direction.ASC;
                }
            }

            if (StringUtils.isNumeric(limit)) {
                this.limit = Integer.parseInt(limit);
            } else {
                this.limit = DEFAULT_LIMIT;
            }

            if (StringUtils.isNumeric(offset)) {
                this.offset = Integer.parseInt(offset);
            } else {
                this.offset = DEFAULT_OFFSET;
            }
        }

        public Sort.Direction getOrder() {
            return order;
        }

        public String getSort() {
            return sort;
        }

        public int getLimit() {
            return limit;
        }

        public int getOffset() {
            return offset;
        }

    }
}
