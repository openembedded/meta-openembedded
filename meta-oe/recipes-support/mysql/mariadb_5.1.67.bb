require ${PN}_${PV}.inc

DEPENDS += "mariadb-native"

PROVIDES += "mysql5"

RPROVIDES_${PN}-client = "mysql5-client"
RREPLACES_${PN}-client = "mysql5-client"
RCONFLICTS_${PN}-client = "mysql5-client"

RPROVIDES_${PN}-server = "mysql5-server"
RREPLACES_${PN}-server = "mysql5-server"
RCONFLICTS_${PN}-server = "mysql5-server"

