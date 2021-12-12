package middleware

import (
	"context"
	"fmt"

	"github.com/cloudwego/kitex/pkg/streaming"

	"github.com/cloudwego/kitex/pkg/endpoint"
)

func RequestLogMW(next endpoint.Endpoint) endpoint.Endpoint {
	return func(ctx context.Context, request, response interface{}) error {
		if args, ok := request.(*streaming.Args); ok {
			args.Stream = &StreamWrapper{
				args.Stream,
			}
		}
		err := next(ctx, request, response)
		return err
	}
}

type StreamWrapper struct {
	streaming.Stream
}

func (s *StreamWrapper) Context() context.Context {
	return s.Stream.Context()
}

func (s *StreamWrapper) RecvMsg(m interface{}) error {
	fmt.Printf("Request : %+v\n", m)
	return s.Stream.RecvMsg(m)
}

func (s *StreamWrapper) SendMsg(m interface{}) error {
	fmt.Printf("Response : %+v\n", m)
	return s.Stream.SendMsg(m)
}

func (s *StreamWrapper) Close() error {
	return s.Stream.Close()
}
