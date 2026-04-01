PACKAGECONFIG:append:qcom = " ${@bb.utils.contains('MACHINE_FEATURES', 'phone', ' modemmanager ', '', d)}"
