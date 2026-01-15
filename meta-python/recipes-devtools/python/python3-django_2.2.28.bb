require python-django.inc

# Pin to 2.2.x LTS releases ONLY for this recipe
UPSTREAM_CHECK_REGEX = "/${PYPI_PACKAGE}/(?P<pver>(2\.2\.\d*)+)/"

inherit setuptools3

SRC_URI += "file://CVE-2023-31047.patch \
            file://CVE-2023-36053.patch \
            file://CVE-2023-41164.patch \
            file://CVE-2023-43665.patch \
            file://CVE-2023-46695.patch \
            file://CVE-2024-24680.patch \
            file://CVE-2024-42005.patch \
            file://CVE-2024-38875.patch \
            file://CVE-2023-23969.patch \
            file://CVE-2024-39614.patch \
            file://CVE-2024-41989-0001.patch \
            file://CVE-2024-41989-0002.patch \
            file://CVE-2024-41989-0003.patch \
            file://CVE-2024-41989-0004.patch \
            file://CVE-2024-41990.patch \
            file://CVE-2024-41991.patch \
            file://CVE-2024-45230.patch \
            file://CVE-2024-45231.patch \
            file://CVE-2024-53907.patch \
            file://CVE-2024-27351.patch \
            file://CVE-2025-26699.patch \
            file://CVE-2024-56374.patch \
            file://CVE-2025-57833.patch \
            file://CVE-2024-39329.patch \
            file://CVE-2024-39330.patch \
            file://CVE-2025-32873.patch \
            file://CVE-2025-64459.patch \
            file://Fix-undefined-_lazy_re_compile.patch \
            file://Fix-missing-JSONField-in-django.db.mo.patch \
            file://0001-Fixed-35172-Fixed-intcomma-for-string-floats.patch \
            file://0001-implement-group-method-for-FakeMatch.patch \
            file://0001-fix-ipv6-test.patch \
            file://0001-Fixed-32298-Fixed-URLValidator-hostname-length-valid.patch \
            file://0001-Fixed-33367-Fixed-URLValidator-crash-in-some-edge-ca.patch \
            file://0001-Fix-tag_strip-tests.patch \
            file://0001-Fixed-inspectdb.tests.InspectDBTestCase.test_custom_.patch \
            file://0001-Fixed-test_utils.tests.HTMLEqualTests.test_parsing_e.patch \
            file://0001-Made-RemoteTestResultTest.test_pickle_errors_detecti.patch \
            file://0001-fix-quote-type-in-expected-error-message.patch \
            file://0001-Fix-patch-for-CVE-2023-36053.patch \
            "

SRC_URI[sha256sum] = "0200b657afbf1bc08003845ddda053c7641b9b24951e52acd51f6abda33a7413"

RDEPENDS:${PN} += "\
    ${PYTHON_PN}-sqlparse \
"
