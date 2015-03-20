# This is my README

#
# Create Setup Commands:
#
> mvn archetype:generate -DgroupId=se.util.eif.logging -DartifactId=eif-logger -Dversion=1.0.0-SNAPSHOT

#
# Eclipse:
#
> cd eif-logger
> mvn eclipse:eclipse

#
# Maven 3
#
> mvn clean
> mvn package
> mvn install

#
# Generate EifMetaData members
#
grep protected target/generated-sources/xjc/se/util/namespaces/eif/logging/auditlog/_0001/AuditLog.java > members.txt
grep protected target/generated-sources/xjc/se/util/namespaces/eif/logging/systemlog/_0001/SystemLog.java >> members.txt
sort members.txt


