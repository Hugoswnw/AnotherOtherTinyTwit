import requests
import random
import string

url = "http://localhost:8080/_ah/api/tinytwitsmah/v1/"

# r = requests.post(url+'user_add', params={"username": "bob"})

#
# r = requests.post(url+'follow_add', params={"follower": "bob", "followed": "daniel"})
# r = requests.delete(url+'follow_delete', params={"follower": "daniel", "followed": "daniel"})

#
# for i in range(3):
#     text = ''.join(random.choice(string.ascii_uppercase) for _ in range(180))
#     r = requests.post(url+'message_add', params={"author": "daniel", "content": text})

# r = requests.post(url+'message_add', params={"author": "bob", "content": "oui #on s'amuse #non"})

#print requests.get(url+'message_get_timeline', params={"username": "bob", "amount": 5}).text
print requests.get(url+'hashtag_get_messages', params={"word": "#on", "amount": 5}).text
