document.addEventListener('DOMContentLoaded', ()=> {
    const stripe = Stripe(window.STRIPE_PUBLIC_KEY);
    const btn = document.getElementById("checkout-button");
    console.log("[[${stripePublicKey}]]");
    btn.addEventListener('click', async () => {
       const res = await fetch('');
       const { sessionId } = await res.json();
       const { error } = await stripe.redirectToCheckout({ sessionId });
       if (error) alert(error.message);
    });
});