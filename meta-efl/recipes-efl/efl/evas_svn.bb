require ${BPN}.inc

SRCREV = "${EFL_SRCREV}"
PV = "1.2.1+svnr${SRCPV}"
PR = "${INC_PR}.1"
DEFAULT_PREFERENCE = "-1"

# evas-loader-svg is gone as we don't have esvg and probably won't have anytime soon
# http://www.intesis.hr/news/16-esvg-source
# http://blog.gmane.org/gmane.comp.window-managers.enlightenment.user/page=3
RRECOMMENDS_${BPN} += "evas-loader-generic evas-generic-loader-svn"

SRC_URI = "\
  ${E_SVN}/trunk;module=${SRCNAME};protocol=http;scmdata=keep \
"
S = "${WORKDIR}/${SRCNAME}"
