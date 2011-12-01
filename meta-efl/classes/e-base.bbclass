HOMEPAGE = "http://www.enlightenment.org"
SRCNAME ?= "${BPN}"

# usually tracks svn trunk HEAD
EFL_SRCREV ?= "65808"
# revision when 1.1.0 (eet is actually 1.5.0) was released, 
# so we can lock some release libs to this revision 
# and use EFL_SRCREV to track e17 and elementary
EFL_SRCREV_1.1.0 ?= "65800"
# revision when 1.0.0 was released, for recipes which don't need rebuild so often
EFL_SRCREV_1.0.0 ?= "56356"

ARM_INSTRUCTION_SET = "arm"
