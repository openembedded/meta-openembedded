PRINC := "${@int(PRINC) + 2}"

# Register with logind to make screen/tmux/etc work
do_install_append() {
    echo "session required    pam_systemd.so" >> ${D}${sysconfdir}/pam.d/common-session
}
