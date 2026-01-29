let api = "http://40.0.49.6:4040/api/login";

async function login() {
  try {
    let x = document.getElementById("e").value;
    let y = document.getElementById("p").value;
    let db = {
        email: x,
        password: y
        };
    console.log("\n\t js obj :" + JSON.stringify(db, null, 2));
    let res = await fetch(api, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(db),
    });
    let data = await res.text();
    if (res.ok) {
      alert("  Login success ... ✅ ");
    }
    console.log(" api response : " + data);
  } catch (e) {
    alert(" Login failed .. ❌");
  }
}
login();
