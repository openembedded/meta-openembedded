#!/bin/sh
#
# This script passes BMI state variables to the running JVM.
#
if [ ${BMIBUS_PRODUCT} != "" ] && [ ${BMIBUS_SLOT} != "" ] && [ ${ACTION} != "" ];
then                                                                            
        echo $BMIBUS_PRODUCT 0 $BMIBUS_SLOT $ACTION > /tmp/eventpipe            
fi
