SUMMARY = "Run periodic jobs in PostgreSQL"
DESCRIPTION = "pg_cron is a cron-based job scheduler that runs inside \
PostgreSQL as an extension. Jobs are scheduled from SQL with the usual \
cron syntax and run by a background worker in the server."
HOMEPAGE = "https://github.com/citusdata/pg_cron"
BUGTRACKER = "https://github.com/citusdata/pg_cron/issues"
LICENSE = "PostgreSQL"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b70f68d78dbcc36c5654d795cee59b0d"

SRC_URI = "git://github.com/citusdata/pg_cron.git;protocol=https;branch=main;tag=v${PV}"
SRCREV = "2323864075a50f234235f98e567eb958f923b2b3"

inherit pgxs
