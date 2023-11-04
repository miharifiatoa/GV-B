module Sales.management {
    requires jakarta.persistence;
    requires java.naming;
    requires org.hibernate.orm.core;
    requires org.apache.commons.codec;
    opens org.sales_management.entity;
    exports org.sales_management.entity;
    exports org.sales_management.service;
    exports org.sales_management.session;
}