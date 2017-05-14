# duct-immutant

[![Build Status](https://circleci.com/gh/j0ni/duct-immutant/tree/master.svg?style=shield&circle-token=48abf84ba07b92131d105fd165a3f14b777b0e4d)](https://circleci.com/gh/j0ni/duct-immutant)

Integrant multimethods for running an [Immutant][] server for the [Duct][]
framework.

## Installation

To install, add the following to your project `:dependencies`:

    [duct-immutant "0.1.0-SNAPSHOT"]
    
NOTE: not yet published to clojars

## Usage

The multimethods dispatch off the `:duct-immutant/immutant` key, which
is derived from `:duct.server/http`. The corresponding value is a map
of options for the [Immutant][] `run` function, plus a `:handler` key that
takes a handler function.

For example:

```clojure
{:duct-immutant/immutant
 {:port    3000
  :handler (fn [request]
             {:status  200
              :headers {"Content-Type" "text/plain"}
              :body    "Hello World"})}}
```

A `:logger` key may also be specified, which will be used to log when
the server starts and when it stops. The value of the key should be an
implementation of the `duct.logger/Logger` protocol from the
[duct.logger][] library

[immutant]: http://immutant.org/documentation/current/apidoc/guide-web.html
[duct]: https://github.com/duct-framework/duct
[duct.logger]: https://github.com/duct-framework/logger

## Acknowledgements

All of this is derived directly
from [server.http.jetty](https://github.com/duct-framework/server.http.jetty),
by @weavejester (James Reeves).

## License

Copyright © 2017 Jonathan Irving

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

