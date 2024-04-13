DESCRIPTION = "Twisted is an event-driven networking framework written in Python and licensed under the LGPL. \
Twisted supports TCP, UDP, SSL/TLS, multicast, Unix sockets, a large number of protocols                   \
(including HTTP, NNTP, IMAP, SSH, IRC, FTP, and others), and much more."
HOMEPAGE = "http://www.twistedmatrix.com"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0f8d67f84b6e178c92d471011b2245fc"

SRC_URI[sha256sum] = "32acbd40a94f5f46e7b42c109bfae2b302250945561783a8b7a059048f2d4d31"

PYPI_PACKAGE = "Twisted"

inherit pypi python_setuptools_build_meta

do_install:append() {
    # remove some useless files before packaging
    find ${D} \( -name "*.bat" -o -name "*.c" -o -name "*.h" \) -exec rm -f {} \;
}

PACKAGES += "\
    ${PN}-zsh \
    ${PN}-test \
    ${PN}-protocols \
    ${PN}-conch \
    ${PN}-mail \
    ${PN}-names \
    ${PN}-news \
    ${PN}-runner \
    ${PN}-web \
    ${PN}-words \
    ${PN}-flow \
    ${PN}-pair \
    ${PN}-core \
"

DEPENDS += " \
    python3-incremental-native \
"

RDEPENDS:${PN} = "\
    ${PN}-core \
    ${PN}-conch \
    ${PN}-mail \
    ${PN}-names \
    ${PN}-pair \
    ${PN}-protocols \
    ${PN}-runner \
    ${PN}-web \
    ${PN}-words \
    ${PN}-zsh \
"

RDEPENDS:${PN}-core = "python3-appdirs \
                       python3-asyncio \
                       python3-automat \
                       python3-constantly \
                       python3-core \
                       python3-debugger \
                       python3-hyperlink \
                       python3-incremental \
                       python3-pyhamcrest \
                       python3-pyserial \
                       python3-typing-extensions \
                       python3-unixadmin \
                       python3-zopeinterface \
"
RDEPENDS:${PN}-test = "${PN}"
RDEPENDS:${PN}-conch = "${PN}-core ${PN}-protocols python3-bcrypt python3-cryptography python3-pyasn1 python3-pickle"
RDEPENDS:${PN}-mail = "${PN}-core ${PN}-protocols"
RDEPENDS:${PN}-names = "${PN}-core"
RDEPENDS:${PN}-news = "${PN}-core ${PN}-protocols"
RDEPENDS:${PN}-runner = "${PN}-core ${PN}-protocols"
RDEPENDS:${PN}-web += "${PN}-core ${PN}-protocols"
RDEPENDS:${PN}-words += "${PN}-core"
RDEPENDS:${PN}-flow += "${PN}-core"
RDEPENDS:${PN}-pair += "${PN}-core"

FILES:${PN} = "${PYTHON_SITEPACKAGES_DIR}/${PYPI_PACKAGE}-${PV}.dist-info/*"

FILES:${PN}-test = " \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/test \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/*/test \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/protocols/haproxy/test/ \
"

FILES:${PN}-protocols = " \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/protocols/*.py* \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/protocols/gps/ \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/protocols/mice/ \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/protocols/haproxy \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/protocols/__pycache__/*pyc \
"

FILES:${PN}-zsh = " \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/python/twisted-completion.zsh \
"

FILES:${PN}-conch = " \
    ${bindir}/ckeygen \
    ${bindir}/tkconch \
    ${bindir}/conch \
    ${bindir}/cftp \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/plugins/twisted_conch.py* \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/plugins/__pycache__/twisted_conch*.pyc \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/conch \
"

FILES:${PN}-core = " \
    ${bindir}/twist \
    ${bindir}/twistd \
    ${bindir}/trial \
    ${bindir}/pyhtmlizer \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/*.py* \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/application \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/cred \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/enterprise \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/internet \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/persisted \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/protocols \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/scripts \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/spread \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/tap \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/trial \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/*.py* \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/python/*.py* \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/plugins/*.py* \
    ${PYTHON_SITEPACKAGES_DIR}/Twisted*egg-info \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/logger \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/_threads \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/positioning \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/py.typed \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/__pycache__/*pyc \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/python/__pycache__/*pyc \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/plugins/__pycache__/__init__*.pyc \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/plugins/__pycache__/cred_anonymous*.pyc \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/plugins/__pycache__/cred_file*.pyc \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/plugins/__pycache__/cred_memory*.pyc \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/plugins/__pycache__/cred_sshkeys*.pyc \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/plugins/__pycache__/cred_unix*.pyc \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/plugins/__pycache__/twisted_core*.pyc \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/plugins/__pycache__/twisted_ftp*.pyc \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/plugins/__pycache__/twisted_inet*.pyc \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/plugins/__pycache__/twisted_portforward*.pyc \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/plugins/__pycache__/twisted_reactors*.pyc \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/plugins/__pycache__/twisted_socks*.pyc \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/plugins/__pycache__/twisted_trial*.pyc \
"

FILES:${PN}-mail = " \
    ${bindir}/mailmail \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/plugins/twisted_mail.py* \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/plugins/__pycache__/twisted_mail*.pyc \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/mail \
"

FILES:${PN}-names = " \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/plugins/twisted_names.py* \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/plugins/__pycache__/twisted_names*.pyc \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/names \
"

FILES:${PN}-news = " \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/plugins/twisted_news.py* \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/plugins/__pycache__/twisted_news*.pyc \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/news \
"

FILES:${PN}-runner = " \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/plugins/twisted_runner.py* \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/plugins/__pycache__/twisted_runner*.pyc \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/runner \
"

FILES:${PN}-web = " \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/plugins/twisted_web.py* \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/plugins/__pycache__/twisted_web*.pyc \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/web \
"

FILES:${PN}-words = " \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/plugins/twisted_words.py* \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/plugins/__pycache__/twisted_words*.pyc \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/words \
"

FILES:${PN}-flow = " \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/plugins/twisted_flow.py* \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/plugins/__pycache__/twisted_flow*.pyc \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/flow \
"

FILES:${PN}-pair = " \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/plugins/twisted_pair.py* \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/plugins/__pycache__/twisted_pair*.pyc \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/pair \
"

FILES:${PN}-doc += " \
    ${PYTHON_SITEPACKAGES_DIR}/twisted/python/_pydoctortemplates \
"

