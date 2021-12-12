package handler

import (
	"context"
	"user/kitex_gen/user_service"
)

func Auth(ctx context.Context, req *user_service.AuthenticateRequest) (*user_service.AuthenticateResponse, error) {
	var resp = &user_service.AuthenticateResponse{
		UserId: 2333,
	}
	return resp, nil
}
