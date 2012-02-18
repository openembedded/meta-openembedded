PRINC := "${@int(PRINC) + 3}"

PACKAGECONFIG ??= "systemd"
PACKAGECONFIG[systemd] = "--enable-systemd,--disable-systemd,systemd"
# there is no --enable/--disable option for consolekit and it's not picked by shlibs, so add it to RDEPENDS
PACKAGECONFIG[consolekit] = ",,,consolekit"
