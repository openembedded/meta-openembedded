require netmap.inc

DEPENDS = "virtual/kernel"
do_configure[depends] += "virtual/kernel:do_shared_workdir"

inherit module

CLEANBROKEN = "1"

export INSTALL_MOD_DIR="kernel/netmap-modules"

EXTRA_OECONF = "--kernel-dir=${STAGING_KERNEL_BUILDDIR} \
                --kernel-sources=${STAGING_KERNEL_DIR} \
                --install-mod-path=${D} \
                --driver-suffix="-netmap" \
                "

EXTRA_OECONF += "--no-drivers=ixgbe --no-drivers=virtio_net.c"

LDFLAGS := "${@'${LDFLAGS}'.replace('-Wl,-O1', '')}"
LDFLAGS := "${@'${LDFLAGS}'.replace('-Wl,--as-needed', '')}"

do_configure () {
    cd ${S}/LINUX
    ./configure ${EXTRA_OECONF}
}

do_configure_append () {
    cat >>  ${S}/LINUX/netmap_linux_config.h <<EOF
#define NETMAP_LINUX_HAVE_HRTIMER_MODE_REL
#define NETMAP_LINUX_HAVE_HRTIMER_FORWARD_NOW
#define NETMAP_LINUX_HAVE_PHYS_ADDR_T
#define NETMAP_LINUX_HAVE_ACCESS_ONCE
#define NETMAP_LINUX_HAVE_NETDEV_OPS
#define NETMAP_LINUX_ALLOC_NETDEV_4ARGS
#define NETMAP_LINUX_HAVE_INIT_NET
#define NETMAP_LINUX_HAVE_LIVE_ADDR_CHANGE
#define NETMAP_LINUX_HAVE_TX_SKB_SHARING
#define NETMAP_LINUX_HAVE_UNLOCKED_IOCTL
#define NETMAP_LINUX_HAVE_PERNET_OPS_ID
#define NETMAP_LINUX_VIRTIO_FUNCTIONS
#define NETMAP_LINUX_VIRTIO_FREE_PAGES
#define NETMAP_LINUX_VIRTIO_GET_VRSIZE
#define NETMAP_LINUX_TIMER_RTYPE enum hrtimer_restart
#define NETMAP_LINUX_VIRTIO_MULTI_QUEUE
#define NETMAP_LINUX_HAVE_E1000E_EXT_RXDESC
#define NETMAP_LINUX_HAVE_E1000E_DOWN2
EOF
}

do_compile () {
    cd ${S}/LINUX
    oe_runmake
}

do_install () {
    cd ${S}/LINUX
    oe_runmake install
}
