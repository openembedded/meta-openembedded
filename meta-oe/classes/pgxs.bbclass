#
# Copyright OpenEmbedded Contributors
#
# SPDX-License-Identifier: MIT
#

# Class for PostgreSQL extensions that are built with PGXS

DEPENDS += "postgresql"

# USE_PGXS is for makefiles that can also build inside the PostgreSQL tree
EXTRA_OEMAKE:append = " USE_PGXS=1 PG_CONFIG=pg_config"

pgxs_do_install() {
    oe_runmake install DESTDIR=${D}
}

EXPORT_FUNCTIONS do_install

FILES:${PN} += "${libdir}/postgresql ${datadir}/postgresql"

RDEPENDS:${PN} += "postgresql"
