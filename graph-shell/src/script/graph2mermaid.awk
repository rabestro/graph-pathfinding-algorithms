#!/usr/bin/env gawk --exec
#
# Copyright (c) 2023 Jegors Čemisovs
# License: MIT License
# Repository: https://github.com/rabestro/graph-pathfinding-algorithms
#
BEGIN {
    FS = "[ :,{}]+"
    print "flowchart LR"
}
{
    node = $1
    print node "((" node "))"

    for (i = 2; i < NF; i += 2)
        printf "%s --> |%2d| %s\n", node, $(i + 1), $i
}