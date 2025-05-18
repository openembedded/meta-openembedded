SUMMARY = "Visually render documentation for an API defined with the OpenAPI"
DESCRIPTION = "\
    Swagger UI allows anyone — be it your development team or your end consumers — to visualize and \
    interact with the API’s resources without having any of the implementation logic in place. \
    It’s automatically generated from your OpenAPI (formerly known as Swagger) Specification, \
    with the visual documentation making it easy for back end implementation and client side consumption. \
    "
HOMEPAGE = "https://github.com/swagger-api/swagger-ui"
SECTION = "net"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://github.com/swagger-api/swagger-ui;branch=master;protocol=https"

SRCREV = "74ed0adebfc9c8dd0de2bf8e81495b022a66c083"

S = "${WORKDIR}/git"

do_install() {
    install -d ${D}${localstatedir}/www/openapi/static
    cp -r ${S}/dist/* ${D}${localstatedir}/www/openapi/static
}
