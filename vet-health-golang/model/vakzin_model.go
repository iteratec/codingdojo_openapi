package model

import "time"

type Vakzin struct {
	NAME            string    `json:"name" binding:"required"`                                     //Name of the vakzination
	VAKZINATIONDATE time.Time `json:"vakzinationdate" binding:"required" time_format:"2006-01-02"` //Date when the vakzination was done
}
