package handler

import (
	"context"
	"user/kitex_gen/user_service"
)

func Login(ctx context.Context, req *user_service.LoginRequest) (*user_service.LoginResponse, error) {
	var resp = &user_service.LoginResponse{}
	return resp, nil
}
