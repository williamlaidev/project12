# Python script to demonstrate the usage of dotenv for managing environment variables


from dotenv import load_dotenv
import os

# Load environment variables from .env file
load_dotenv()

# Read API key from environment variables
api_key = os.getenv("MY_API_KEY")

# Check if API key is found
if api_key:
    print("API key found: " + api_key)
else:
    print("API key not found in .env file. Please make sure to set MY_API_KEY.")