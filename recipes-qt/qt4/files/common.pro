#====================================================================================
# common.pro (C) 2004 Michael 'Mickey' Lauer <mickey@Vanille.de>
#====================================================================================
#
# Purpose: This file contains qmake scope rules of common usage
#
# Example: When building a qmake based application using libsdl,
#          use CONFIG+=sdl to add the proper include and library
#          paths and definitions to the resulting Makefile
#
#
# General problem: One has to decide between evaluation at makefile generation time
#                  and evaluation at makefile processing time.
#                  The following example illustrates the difference:
#
# Evaluation at makefile processing time:
#
# sdl {
#     QMAKE_CFLAGS    += `sdl-config --cflags`
#     LIBS            += `sdl-config --libs`
# }
#
# Evalutation at makefile generation time:
#
# sdl {
#     QMAKE_CFLAGS    += $$system( sdl-config --cflags )
#     LIBS            += `sdl-config --libs`
# }
#
# For now I use version 2 which is a bit faster
# See the fine qmake manual for more details
#
#

#=============================================================================
# pthread scope for multithreaded applications
#

pthread {
    LIBS += -lpthread
}

#=============================================================================
# sdl scopes for applications using the Simple Direct Media Layer (SDL)
#

sdl {
    QMAKE_CXXFLAGS  += $$system( sdl-config --cflags )
    DEFINES         += USE_SDL QTOPIA
    LIBS            += $$system( sdl-config --libs )
}

sdl-mixer {
    LIBS            += -lSDL_mixer
}

sdl-image {
    LIBS            += -lSDL_image
}

sdl-font {
    LIBS            += -lSDL_ttf
}

#=============================================================================
#
#

opie {
    DEFINES         += QWS
}

opiecore {
    LIBS            += -lopiecore2
}

opieui {
    CONFIG          += opiecore
    LIBS            += -lopieui2
}

opienet {
    CONFIG          += opiecore
    LIBS            += -lopienet2
}

opiepim {
    CONFIG          += opiecore
    LIBS            += -lopiepim2
}

opiedb {
    CONFIG          += opiecore
    LIBS            += -lopiedb2
}

opiemm {
    CONFIG          += opiecore
    LIBS            += -lopiemm2
}

#=============================================================================
#
#

#=============================================================================
#
#
