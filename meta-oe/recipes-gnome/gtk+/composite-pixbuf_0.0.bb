DESCRIPTION = "Pixbuf performance test"
LICENSE = "Public domain"
DEPENDS = "gtk+" 

SRC_URI = "http://amelang.net/composite_pixbuf.c"

S = "${WORKDIR}"

do_compile() {
        ${CC} ${CFLAGS} composite_pixbuf.c -o composite_pixbuf \
            `pkg-config --cflags gdk-2.0` `pkg-config --libs gdk-2.0` `pkg-config --libs x11` `pkg-config --libs pango` \
	     -lxcb -lXext -lXinerama -lXrandr -lXcursor -lXfixes -lpangoft2-1.0 -lz -lexpat -lxcb-xlib -lXau -lXdmcp  
}	    

do_install() {
        install -d ${D}${bindir} 
        install -m 755 composite_pixbuf ${D}${bindir}
}

SRC_URI[md5sum] = "97edf3cce3353b6cb5f822442a83aa5e"
SRC_URI[sha256sum] = "bd6f9e5dc0a89e9918f78a550f6e688480f044cec2e8674d34c7c6212831d6bd"
