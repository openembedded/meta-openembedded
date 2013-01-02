DESCRIPTION = "All packages required for a base installation of XFCE"
SECTION = "x11/wm"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"
PR = "r5"

inherit packagegroup

RPROVIDES_${PN} += "task-xfce-base"
RREPLACES_${PN} += "task-xfce-base"
RCONFLICTS_${PN} += "task-xfce-base"

RDEPENDS_${PN} = " \
    xfwm4 \
    xfce4-session \     
    xfconf \
    xfdesktop \
    xfce4-panel \
    \
    gtk-xfce-engine \
    \
    xfce4-panel-plugin-actions \
    xfce4-panel-plugin-applicationsmenu \
    xfce4-panel-plugin-clock \
    xfce4-panel-plugin-directorymenu \
    xfce4-panel-plugin-launcher \
    xfce4-panel-plugin-pager \
    xfce4-panel-plugin-separator \
    xfce4-panel-plugin-showdesktop \
    xfce4-panel-plugin-systray \
    xfce4-panel-plugin-tasklist \
    xfce4-panel-plugin-windowmenu \   
    xfce4-settings \
    \
    xfce4-notifyd \
    xfce4-terminal \
    thunar \
    thunar-volman \
"
