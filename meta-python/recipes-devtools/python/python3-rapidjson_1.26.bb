SUMMARY = "Python wrapper around rapidjson"
HOMEPAGE = "https://github.com/python-rapidjson/python-rapidjson"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6fe0b2465366662d7cfa6793ccbab563"

SRC_URI[sha256sum] = "db95af0b5ed5f89d4f91480477a0ea8c7646283e489dd9046e28503e85e08f98"

# Inheriting ptest provides functionality for packaging and installing runtime tests for this recipe
inherit setuptools3 pypi ptest-python-pytest

PYPI_PACKAGE = "python-rapidjson"

SETUPTOOLS_BUILD_ARGS += " --rj-include-dir=${RECIPE_SYSROOT}${includedir}"

DEPENDS += " \
    rapidjson \
"

# Adding required python package for the ptest (pytest and pytest->automake report translation)
RDEPENDS:${PN}-ptest += " \
    python3-pytz \
"

RDEPENDS:${PN} += " \
    python3-core \
"

do_install_ptest:append() {
	# test_endarray_leak asserts sys.getrefcount(j1['v']) == 3, but modern
	# CPython's specialized dict/subscript bytecode holds one fewer temporary
	# reference during the call, so the real count is 2 regardless of
	# environment (reproduces on plain upstream CPython 3.13, unrelated to
	# this target). Upstream test-authoring assumption broken by CPython
	# internals, not a packaging issue.
	sed -i -e "/--automake/ s/$/ -k 'not test_endarray_leak'/" \
		${D}${PTEST_PATH}/run-ptest
}
