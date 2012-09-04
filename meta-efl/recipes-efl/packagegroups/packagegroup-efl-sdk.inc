SDK-EFL = " \
     eina-dev \
     eet-dev \
     evas-dev \
     embryo-dev \
     ecore-dev \
     edbus-dev \
     lua5.1-dev \
     edje-dev \
     elementary-dev \
    "

SDK-EXTRAS ?= ""
SDK-EXTRAS_qemux86 ?= " valgrind lttng-ust-dev"
SDK-EXTRAS_qemux86-64 ?= " lttng-ust-dev"
SDK-EXTRAS_qemuppc ?= " lttng-ust-dev"
SDK-EXTRAS_qemuarm ?= " lttng-ust-dev"
