package dao

import (
	"vet-health-golang/model"
)

var vaccines = make(map[int32]*model.Vaccine)

func SaveVaccine(id int32, vaccine model.Vaccine) bool {
	if _, isIn := vaccines[id]; isIn {
		return false
	} else {
		vaccines[id] = &vaccine
		return true
	}
}

func FindVaccine(id int32) *model.Vaccine {
	return vaccines[id]
}
