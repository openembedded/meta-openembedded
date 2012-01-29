DESCRIPTION = "All packages for full XFCE installation"
SECTION = "x11/wm"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

PR = "r1"

inherit task

# mandatory
RDEPENDS_${PN} = " \
    task-xfce-base \
"

# nice to have
RRECOMMENDS_${PN} = " \
    xfwm4-theme-daloa \
    xfwm4-theme-kokodi \
    xfwm4-theme-moheli \
    xfwm4-theme-sassandra \
    xfwm4-theme-stoneage \
    xfwm4-theme-therapy \
    xfwm4-theme-tyrex \
    xfwm4-theme-wallis \
    \
    xfce4-icon-theme \
    \
    xfce4-cpufreq-plugin \
    xfce4-cpugraph-plugin \
    xfce4-datetime-plugin \
    xfce4-eyes-plugin \
    xfce4-clipman-plugin \
    xfce4-diskperf-plugin \
    xfce4-netload-plugin \
    xfce4-genmon-plugin \
    \
    xfce4-appfinder \
    xfce4-screenshooter \
    ristretto \
"
