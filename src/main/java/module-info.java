module Sales.management {
    requires jakarta.persistence;
    requires java.naming;
    requires org.hibernate.orm.core;
    opens org.sales_management.entity;
    exports org.sales_management.entity;
    exports org.sales_management.service;
    exports org.sales_management.session;
}