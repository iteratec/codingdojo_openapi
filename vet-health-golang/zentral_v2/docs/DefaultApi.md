# {{classname}}

All URIs are relative to *https://api.server.test*

Method | HTTP request | Description
------------- | ------------- | -------------
[**FetchAnimalTypeById**](DefaultApi.md#FetchAnimalTypeById) | **Get** /v2/animals/{id} | 
[**FetchAnimalTypes**](DefaultApi.md#FetchAnimalTypes) | **Get** /v2/animals | 
[**RegisterAnimalType**](DefaultApi.md#RegisterAnimalType) | **Post** /v2/animals | 

# **FetchAnimalTypeById**
> AnimalEntry FetchAnimalTypeById(ctx, id)


### Required Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
  **id** | [**int32**](.md)|  | 

### Return type

[**AnimalEntry**](AnimalEntry.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **FetchAnimalTypes**
> []AnimalEntry FetchAnimalTypes(ctx, )


### Required Parameters
This endpoint does not need any parameter.

### Return type

[**[]AnimalEntry**](AnimalEntry.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **RegisterAnimalType**
> InlineResponse201 RegisterAnimalType(ctx, body)


### Required Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
  **body** | [**interface{}**](interface{}.md)| Inputs for the creation of a new entry in the library | 

### Return type

[**InlineResponse201**](inline_response_201.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

