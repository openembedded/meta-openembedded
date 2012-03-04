DESCRIPTION = "Twisted is an event-driven networking framework written in Python and licensed under the LGPL. \
Twisted supports TCP, UDP, SSL/TLS, multicast, Unix sockets, a large number of protocols                   \
(including HTTP, NNTP, IMAP, SSH, IRC, FTP, and others), and much more."
HOMEPAGE = "http://www.twistedmatrix.com"
SECTION = "console/network"

#twisted/topfiles/NEWS:655: - Relicensed: Now under the MIT license, rather than LGPL.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c3159ad36d96a939fcd8f2c2c9b9d08a"

SRC_URI = "http://tmrc.mit.edu/mirror/twisted/Twisted/10.2/Twisted-${PV}.tar.bz2 "
SRC_URI[md5sum] = "73da62c793269eade8121da336b01ba5"
SRC_URI[sha256sum] = "562ed61c18aa72da99c23fb19c2c101d178995eb3a78ab3c09560a613e180c84"

S = "${WORKDIR}/Twisted-${PV}"

inherit setuptools

do_install_append() {
    # remove some useless files before packaging
    find ${D} -name "*.bat" -o -name "*.c" -o -name "*.h" -exec rm {} \;
}

PACKAGES += "\
  ${PN}-zsh \
  ${PN}-test \
  ${PN}-protocols \
  ${PN}-bin \
  ${PN}-conch \
  ${PN}-lore \
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

RDEPENDS_${PN} = "python-core python-zopeinterface"
RDEPENDS_${PN} += "\
  ${PN}-bin \
  ${PN}-conch \
  ${PN}-lore \
  ${PN}-mail \
  ${PN}-names \
  ${PN}-news \
  ${PN}-runner \
  ${PN}-web \
  ${PN}-words \
"

ALLOW_EMPTY_${PN} = "1"
FILES_${PN} = ""

FILES_${PN}-test = " \
  ${libdir}/${PYTHON_DIR}/site-packages/twisted/test \
  ${libdir}/${PYTHON_DIR}/site-packages/twisted/*/test \
"

FILES_${PN}-protocols = " \
  ${libdir}/${PYTHON_DIR}/site-packages/twisted/protocols/ \
"

FILES_${PN}-zsh = " \
  ${libdir}/${PYTHON_DIR}/site-packages/twisted/python/zsh \
  ${libdir}/${PYTHON_DIR}/site-packages/twisted/python/zshcomp.* \
"

FILES_${PN}-bin = " \
  ${libdir}/${PYTHON_DIR}/site-packages/twisted/protocols/_c_urlarg.so \
  ${libdir}/${PYTHON_DIR}/site-packages/twisted/spread/cBanana.so \
"

FILES_${PN}-conch = " \
  ${bindir}/ckeygen \
  ${bindir}/tkconch \
  ${bindir}/conch \
  ${bindir}/conchftp \
  ${bindir}/cftp \
  ${libdir}/${PYTHON_DIR}/site-packages/twisted/plugins/twisted_conch.py* \
  ${libdir}/${PYTHON_DIR}/site-packages/twisted/conch  \
"

FILES_${PN}-core = " \
${bindir}/manhole \
${bindir}/mktap \
${bindir}/twistd \
${bindir}/tap2deb \
${bindir}/tap2rpm \
${bindir}/tapconvert \
${bindir}/tkmktap \
${bindir}/trial \
${bindir}/easy_install* \
${bindir}/pyhtmlizer \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/*.so \
${libdir}/${PYTHON_DIR}/site-packages/twisted/*.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/plugins/__init__.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/plugins/notestplugin.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/plugins/testplugin.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/plugins/twisted_ftp.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/plugins/twisted_inet.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/plugins/twisted_manhole.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/plugins/twisted_portforward.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/plugins/twisted_socks.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/plugins/twisted_telnet.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/plugins/twisted_trial.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/plugins/dropin.cache \
${libdir}/${PYTHON_DIR}/site-packages/twisted/application \
${libdir}/${PYTHON_DIR}/site-packages/twisted/cred \
${libdir}/${PYTHON_DIR}/site-packages/twisted/enterprise \
${libdir}/${PYTHON_DIR}/site-packages/twisted/internet \
${libdir}/${PYTHON_DIR}/site-packages/twisted/manhole \
${libdir}/${PYTHON_DIR}/site-packages/twisted/manhole \
${libdir}/${PYTHON_DIR}/site-packages/twisted/persisted \
${libdir}/${PYTHON_DIR}/site-packages/twisted/protocols\
${libdir}/${PYTHON_DIR}/site-packages/twisted/python\
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/timeoutqueue.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/filepath.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/dxprofile.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/plugin.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/htmlizer.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/__init__.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/dispatch.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/hook.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/threadpool.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/otp.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/usage.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/roots.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/versions.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/urlpath.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/util.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/components.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/logfile.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/runtime.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/reflect.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/context.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/threadable.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/rebuild.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/failure.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/lockfile.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/formmethod.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/finalize.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/win32.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/dist.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/shortcut.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/zipstream.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/release.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/syslog.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/log.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/compat.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/zshcomp.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/procutils.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/text.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/_twisted_zsh_stub \
${libdir}/${PYTHON_DIR}/site-packages/twisted/scripts/ \
${libdir}/${PYTHON_DIR}/site-packages/twisted/spread/ \
${libdir}/${PYTHON_DIR}/site-packages/twisted/tap/ \
${libdir}/${PYTHON_DIR}/site-packages/twisted/trial/ \
${libdir}/${PYTHON_DIR}/site-packages/twisted/__init__.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/_version.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/copyright.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/im.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/*.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/python/*.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/plugins/*.py* \
"

FILES_${PN}-lore = " \
${bindir}/bookify \
${bindir}/lore \
${libdir}/${PYTHON_DIR}/site-packages/twisted/plugins/twisted_lore.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/lore \
"

FILES_${PN}-mail = " \
${bindir}/mailmail \
${libdir}/${PYTHON_DIR}/site-packages/twisted/plugins/twisted_mail.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/mail \
"

FILES_${PN}-names = " \
${libdir}/${PYTHON_DIR}/site-packages/twisted/plugins/twisted_names.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/names \
"

FILES_${PN}-news = " \
${libdir}/${PYTHON_DIR}/site-packages/twisted/plugins/twisted_news.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/news \
"

FILES_${PN}-runner = " \
${libdir}/site-packages/twisted/runner/portmap.so \
${libdir}/${PYTHON_DIR}/site-packages/twisted/runner\
"

FILES_${PN}-web = " \
${bindir}/websetroot \
${libdir}/${PYTHON_DIR}/site-packages/twisted/plugins/twisted_web.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/web\
"

FILES_${PN}-words = " \
${bindir}/im \
${libdir}/${PYTHON_DIR}/site-packages/twisted/plugins/twisted_words.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/words\
"

FILES_${PN}-flow = " \
${libdir}/${PYTHON_DIR}/site-packages/twisted/plugins/twisted_flow.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/flow \"

FILES_${PN}-pair = " \
${libdir}/${PYTHON_DIR}/site-packages/twisted/plugins/twisted_pair.py* \
${libdir}/${PYTHON_DIR}/site-packages/twisted/pair \
"

FILES_${PN}-dbg += " \
${libdir}/${PYTHON_DIR}/site-packages/twisted/*/.debug \
${libdir}/${PYTHON_DIR}/site-packages/twisted/*/*/.debug \
"

