
#!/usr/bin/env gawk --exec
#
# Copyright (c) 2023 Jegors Čemisovs
# License: MIT License
# Repository: https://github.com/rabestro/graph-pathfinding-algorithms
#
BEGIN {
    FS = "[ :,{}]+"

    match(FILENAME, /([^/]+)\.yaml/, GraphName)
    print "@startdot"
    print "digraph", GraphName[1], "{"
    print "fontname=\"Helvetica,Arial,sans-serif\""
    print "node [fontname=\"Helvetica,Arial,sans-serif\"]"
    print "edge [fontname=\"Helvetica,Arial,sans-serif\"]"
    print "node [color=lightblue2, style=filled, shape=circle];"
}
{
    print $1

    for (i = 2; i < NF; i += 2)
        print $1, "->", $i, "[label=" $(i + 1) "];"
}
END {
    print "}"
    print "@enddot"
}