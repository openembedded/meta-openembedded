SUMMARY = "Cross-platform locking library"
DESCRIPTION = "Portalocker is a library to provide an easy API to file locking"
LICENSE = "BSD-3-Clause"

LIC_FILES_CHKSUM = "file://LICENSE;md5=152634da660a374ca18c0734ed07c63c"

SRC_URI[sha256sum] = "7ac85bb2aa3c7873bf2ac72586fc6154afa4671ac786994a16617a6b7ccb3e5e"

DEPENDS += "python3-setuptools-scm-native"

inherit pypi python_uv_build ptest

SRC_URI += " \
	file://run-ptest \
"

RDEPENDS:${PN}-ptest += " \
	python3-multiprocessing \
	python3-pytest \
	python3-redis \
	python3-typing-extensions \
	python3-unittest-automake-output \
	redis \
"

do_install_ptest() {
	install -d ${D}${PTEST_PATH}/portalocker_tests
	cp -rf ${S}/portalocker_tests/* ${D}${PTEST_PATH}/portalocker_tests/
	rm -rf ${D}${PTEST_PATH}/portalocker_tests/test_combined.py
	# the redis lock tests need fakeredis, which isn't packaged
	rm -f ${D}${PTEST_PATH}/portalocker_tests/test_redis.py ${D}${PTEST_PATH}/portalocker_tests/test_redis_random_sleep.py
	# test_readme.py runs the examples from README.md
	install -m 0644 ${S}/README.md ${D}${PTEST_PATH}/
}

RDEPENDS:${PN} += " \
	python3-fcntl \
	python3-logging \
"

BBCLASSEXTEND = "native nativesdk"
