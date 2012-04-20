DEPENDS += "mysql5 postgresql sqlite"
QT_SQL_DRIVER_FLAGS = "-no-sql-ibase -plugin-sql-mysql -no-sql-odbc -plugin-sql-psql -plugin-sql-sqlite2 -plugin-sql-sqlite"
QT_CONFIG_FLAGS += "-I${STAGING_INCDIR}/mysql \
                    -I${STAGING_INCDIR}/postgresql"

PRINC := "${@int(PRINC) + 1}"
