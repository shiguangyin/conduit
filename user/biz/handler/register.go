package handler

import (
	"context"
	"user/kitex_gen/user_service"
)

func Register(ctx context.Context, req *user_service.RegisterRequest) (*user_service.RegisterResponse, error) {
	var resp = &user_service.RegisterResponse{}
	return resp, nil
}
